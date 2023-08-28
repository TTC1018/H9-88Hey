package softeer.h9.hey.auth.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

import softeer.h9.hey.auth.domain.RefreshTokenEntity;
import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.exception.InvalidTokenException;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.RefreshTokenRepository;
import softeer.h9.hey.auth.repository.UserRepository;
import softeer.h9.hey.auth.service.JwtTokenProvider;
import softeer.h9.hey.auth.service.PasswordEncoder;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("AuthController 테스트")
class AuthControllerTest {

	@SpyBean
	JwtTokenProvider jwtTokenProvider;

	@SpyBean
	RefreshTokenRepository refreshTokenRepository;

	@SpyBean
	PasswordEncoder passwordEncoder;

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
					.content(objectMapper.writeValueAsString(new JoinRequest("email@email.com", "password", userName))))
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
					.content(objectMapper.writeValueAsString(new JoinRequest("email@email.com", "testPassword", "userName"))))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()))
			.andExpect(jsonPath("$.message").value(JoinException.DUPLICATED_EMAIL_MESSAGE));
	}

	@Test
	@DisplayName("로그인 요청을 정상적으로 처리한다.")
	void signInTest() throws Exception {
		String email = "email@email.com";
		String password = "password";
		String userName = "userName";
		User user = new User(email, password, userName);
		user.setId(1);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(passwordEncoder.compare(anyString(), anyString())).thenReturn(true);

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
		String unEnrolledEmail = "email@email.com";

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
		String email = "email@email.com";
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

	@Test
	@DisplayName("Refresh Token을 헤더에 담아 엑세스 토큰을 요청하면, 갱신된 엑세스 토큰과 리프레시 토큰을 발급해준다.")
	void republishAccessTokenTest() throws Exception {
		Map<String, Object> claims = Map.of("sub", "1", "userName", "userName123");
		LocalDateTime expiredTime  = LocalDateTime.now().plusHours(1);
		List<RefreshTokenEntity> refreshTokenEntities = List.of(new RefreshTokenEntity(1, "refreshToken", expiredTime));

		doReturn(claims).when(jwtTokenProvider).getClaimsFromToken("refreshToken");
		doReturn(refreshTokenEntities).when(refreshTokenRepository).findByUserId(anyInt());
		doNothing().when(refreshTokenRepository).deleteById(anyInt());

		mockMvc.perform(
				post("/auth/access-token/reissue")
					.header("Authorization", "Bearer refreshToken"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.accessToken").exists(),
				jsonPath("$.data.refreshToken").exists(),
				jsonPath("$.data.userName").exists()
			);
	}


	@Test
	@DisplayName("유효하지 않은 Refresh Token을 헤더에 담아 엑세스 토큰을 요청하면 401 예외를 던진다.")
	void republishAccessTokenFailTest() throws Exception {
		mockMvc.perform(
				post("/auth/access-token/reissue")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer jwttoken12edw"))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.message").value(InvalidTokenException.INVALID_TOKEN_EXCEPTION));
	}

	@Test
	@DisplayName("입력받은 AccessToken이 유효한지 확인하고 유효하다면 userName을 반환한다.")
	void validateTokenValidateTest() throws Exception {
		mockMvc.perform(
				get("/auth/access-token/validate")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization",
						"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.statusCode").value(200))
			.andExpect(jsonPath("$.data.userName").value("test"));
	}


	@Test
	@DisplayName("유효하지 않은 토큰으로 유효성 검증을 요청하면, 401 응답을 내보낸다.")
	void invalidateTokenValidateTest() throws Exception {
		mockMvc.perform(
				get("/auth/access-token/validate")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization",
						"Bearer xx"))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.statusCode").value(401))
			.andExpect(jsonPath("$.message").value(InvalidTokenException.INVALID_TOKEN_EXCEPTION));
	}
}
