package softeer.h9.hey.auth.controller;

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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.UserRepository;
import softeer.h9.hey.auth.service.JwtTokenProvider;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("AuthController 테스트")
class AuthControllerTest {

	@SpyBean
	JwtTokenProvider jwtTokenProvider;

	@SpyBean
	UserRepository userRepository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("회원가입 요청 정상처리")
	void signUpTest() throws Exception {
		String userName = "userName";

		mockMvc.perform(
				post("/auth/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest("email", "password", userName))))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.accessToken").exists(),
				jsonPath("$.data.refreshToken").exists(),
				jsonPath("$.data.userName").value(userName)
			);
	}

	@Test
	@DisplayName("이미 가입되어있는 회원 ID인 경우 회원가입에 실패한다. ")
	void duplicatedIdSignUpFailTest() throws Exception {

		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(Mockito.mock(User.class)));

		mockMvc.perform(
				post("/auth/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest("email", "testPassword", "userName"))))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()))
			.andExpect(jsonPath("$.message").value(JoinException.LOGIN_FAIL_MESSAGE));
	}

	@Test
	@DisplayName("로그인 요청을 정상적으로 처리한다.")
	void signInTest() throws Exception {
		String email = "email";
		String password = "password";
		String userName = "userName";
		User user = new User(email, password, userName);
		user.setId(1);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		mockMvc.perform(
				post("/auth/signin")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new LoginRequest(email, password))))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.accessToken").exists(),
				jsonPath("$.data.refreshToken").exists(),
				jsonPath("$.data.userName").exists()
			);
	}

	@Test
	@DisplayName("등록되어있지 않은 ID로 로그인 요청이 들어오는 경우 로그인에 실패한다.")
	void notEnrolledIdTest() throws Exception {
		String unEnrolledEmail = "email";

		when(userRepository.findByEmail(unEnrolledEmail)).thenReturn(Optional.empty());

		mockMvc.perform(
				post("/auth/signin")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new JoinRequest(unEnrolledEmail, "wrongPassword", "name"))))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.message").value(LoginException.LOGIN_FAIL_MESSAGE));
	}

	@Test
	@DisplayName("비밀번호가 틀린 경우 로그인에 실패한다.")
	void wrongPasswordLoginTest() throws Exception {
		String email = "email";
		String password = "password";
		String userName = "userName";
		User user = new User(email, password, userName);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		mockMvc.perform(
				post("/auth/signin")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(new LoginRequest(email, "wrongPassword"))))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.message").value(LoginException.LOGIN_FAIL_MESSAGE));
	}
}
