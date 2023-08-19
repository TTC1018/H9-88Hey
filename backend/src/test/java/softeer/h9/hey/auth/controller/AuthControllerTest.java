package softeer.h9.hey.auth.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("AuthController Test")
class AuthControllerTest {

	@SpyBean
	UserRepository userRepository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("회원가입 요청 정상처리")
	void signUpTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				post("/auth/sign-up")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest("userId", "password", "userName"))))
			.andExpect(status().isCreated())
			.andReturn();

		byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
		SignUpResponse response = objectMapper.readValue(contentAsByteArray, SignUpResponse.class);

		assertThat(mvcResult.getResponse().getHeader("Authorization")).startsWith("Bearer ");
		assertThat(response.getRefreshToken()).isNotNull();
		assertThat(response.getUserName()).isEqualTo("userName");
	}

	@Test
	@DisplayName("이미 가입되어있는 회원 ID인 경우 회원가입에 실패한다. ")
	void duplicatedIdSignUpFailTest() throws Exception {

		when(userRepository.findByUserId(anyString())).thenReturn(Optional.of(Mockito.mock(User.class)));

		mockMvc.perform(
				post("/auth/sign-up")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest("testId", "testPassword", "userName"))))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()))
			.andExpect(jsonPath("$.message").value(LoginException.LOGIN_FAIL_MESSAGE));
	}

	@Test
	@DisplayName("로그인 요청을 정상적으로 처리한다.")
	void signInTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				post("/auth/sign-in")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new LoginRequest("testId", "testPassword"))))
			.andExpect(status().isOk())
			.andReturn();

		byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
		SignUpResponse response = objectMapper.readValue(contentAsByteArray, SignUpResponse.class);

		assertThat(mvcResult.getResponse().getHeader("Authorization")).startsWith("Bearer ");
		assertThat(response.getRefreshToken()).isNotNull();
		assertThat(response.getUserName()).isEqualTo("userName");
	}

	@Test
	@DisplayName("등록되어있지 않은 ID로 로그인 요청이 들어오는 경우 로그인에 실패한다.")
	void notEnrolledIdTest() throws Exception {
		String unEnrolled = "testId";

		when(userRepository.findByUserId(unEnrolled)).thenReturn(Optional.empty());

		mockMvc.perform(
				post("/auth/sign-in")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest(unEnrolled, "wrongPassword", "name"))))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.message").value(LoginException.LOGIN_FAIL_MESSAGE));
	}

	@Test
	@DisplayName("비밀번호가 틀린 경우 로그인에 실패한다.")
	void wrongPasswordLoginTest() throws Exception {
		String userId = "userId";
		String password = "password";
		String userName = "userName";
		User user = new User(userId, password, userName);

		when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));

		mockMvc.perform(
				post("/auth/sign-in")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new LoginRequest(userId, "wrongPassword"))))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.message").value(LoginException.LOGIN_FAIL_MESSAGE));
	}
}
