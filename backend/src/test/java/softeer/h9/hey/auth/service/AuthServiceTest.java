package softeer.h9.hey.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.auth.domain.RefreshTokenEntity;
import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.AccessTokenRequest;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.dto.request.ValidatedUserRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.dto.response.UserNameResponse;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.RefreshTokenRepository;
import softeer.h9.hey.auth.repository.UserRepository;

@DisplayName("인증 관련 서비스 기능 테스트")
class AuthServiceTest {
	private final JwtTokenProvider tokenProvider = Mockito.mock(JwtTokenProvider.class);
	private final UserRepository userRepository = Mockito.mock(UserRepository.class);
	private final RefreshTokenRepository refreshTokenRepository = Mockito.mock(RefreshTokenRepository.class);
	private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
	private final RefreshTokenAsyncExecutor refreshTokenAsyncExecutor = Mockito.mock(RefreshTokenAsyncExecutor.class);
	private final AuthService authService = new AuthService(tokenProvider, userRepository, refreshTokenRepository,
		passwordEncoder, refreshTokenAsyncExecutor);

	@Test
	@DisplayName("아이디와 비밀번호를 전달하여 회원가입을 한다.")
	void joinTest() {
		JoinRequest joinRequest = new JoinRequest("email", "password", "name");

		User user = new User("email", "password", "name");
		user.setId(1);
		when(tokenProvider.generateAccessToken(anyInt(), any())).thenReturn("accessToken");
		when(tokenProvider.generateRefreshToken(anyInt(), any())).thenReturn("refreshToken");
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		when(userRepository.save(any())).thenReturn(user);

		TokenResponse tokenResponse = authService.join(joinRequest);

		Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
	}

	@Test
	@DisplayName("똑같은 ID로 회원 가입 요청이 들어올 경우 예외를 던진다.")
	void duplicateJoinTest() {
		String email = "email";
		JoinRequest joinRequest = new JoinRequest(email, "password", "name");

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(mock(User.class)));

		Assertions.assertThatThrownBy(() -> authService.join(joinRequest))
			.isInstanceOf(JoinException.class);
	}

	@Test
	@DisplayName("로그인 테스트")
	void loginTest() {
		String email = "email";
		String password = "password";
		String name = "name";
		User user = new User(email, password, name);
		user.setId(1);

		LoginRequest loginRequest = new LoginRequest(user.getEmail(), user.getPassword());

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(tokenProvider.generateAccessToken(anyInt(), any())).thenReturn("accessToken");
		when(tokenProvider.generateRefreshToken(anyInt(), any())).thenReturn("refreshToken");
		when(passwordEncoder.compare(anyString(), anyString())).thenReturn(true);
		TokenResponse tokenResponse = authService.login(loginRequest);

		Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
	}

	@Test
	@DisplayName("비밀번호가 틀린 경우 로그인 실패 테스트")
	void loginFailTest1() {
		String email = "email";
		String password = "password";
		String name = "name";
		User user = new User(email, password, name);

		LoginRequest loginRequest = new LoginRequest(email, "wrongPassword");

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		Assertions.assertThatThrownBy(() -> authService.login(loginRequest))
			.isInstanceOf(LoginException.class);
	}

	@Test
	@DisplayName("요청 ID로 등록된 회원이 없는 경우 로그인 실패 테스트")
	void loginFailTest2() {
		String email = "email";
		LoginRequest loginRequest = new LoginRequest(email, "password");

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		Assertions.assertThatThrownBy(() -> authService.login(loginRequest))
			.isInstanceOf(LoginException.class);
	}

	@Test
	@DisplayName("Access Token 재발급 요청 기능")
	void republishAccessTokenTest() {
		AccessTokenRequest accessTokenRequest = new AccessTokenRequest("refreshToken");
		LocalDateTime expiredTime = LocalDateTime.now().plusHours(1);

		when(tokenProvider.generateAccessToken(anyInt(), any())).thenReturn("accessToken");
		when(tokenProvider.generateRefreshToken(anyInt(), any())).thenReturn("refreshToken");
		when(tokenProvider.getClaimsFromToken(anyString()))
			.thenReturn(Map.of("sub", "1", "userName", "userName123"));
		when(refreshTokenRepository.findByUserId(anyInt()))
			.thenReturn(List.of(new RefreshTokenEntity(1, "refreshToken", expiredTime)));
		doNothing().when(refreshTokenRepository).deleteById(anyInt());

		TokenResponse tokenResponse = authService.republishAccessToken(accessTokenRequest);

		Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
	}

	@Test
	@DisplayName("Access Token으로부터 사용자 이름을 가져오는 기능 테스트")
	void getValidatedUserNameTest() {
		String expectedUserName = "expectedUserName";
		String accessToken = "abcdefgh";
		ValidatedUserRequest validatedUserRequest = new ValidatedUserRequest(accessToken);

		when(tokenProvider.getUserNameFromToken(accessToken))
			.thenReturn(expectedUserName);

		UserNameResponse validatedUser = authService.getValidatedUser(validatedUserRequest);
		String userName = validatedUser.getUserName();

		assertEquals(expectedUserName, userName);
	}
}
