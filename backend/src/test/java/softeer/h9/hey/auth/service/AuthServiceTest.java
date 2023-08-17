package softeer.h9.hey.auth.service;

import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.UserRepository;

@DisplayName("인증 관련 서비스 기능 테스트")
class AuthServiceTest {
	private final JwtTokenProvider tokenProvider = Mockito.mock(JwtTokenProvider.class);
	private final UserRepository userRepository = Mockito.mock(UserRepository.class);
	private final AuthService authService = new AuthService(tokenProvider, userRepository);

	@Test
	@DisplayName("아이디와 비밀번호를 전달하여 회원가입을 한다.")
	void joinTest() {
		JoinRequest joinRequest = new JoinRequest("userId", "password");

		User user = new User(1, "userId", "password");
		when(tokenProvider.generateAccessToken(anyString())).thenReturn("accessToken");
		when(tokenProvider.generateRefreshToken(anyString())).thenReturn("refreshToken");
		when(userRepository.save(any())).thenReturn(user);

		TokenResponse tokenResponse = authService.join(joinRequest);

		Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
	}

	@Test
	@DisplayName("똑같은 ID로 회원 가입 요청이 들어올 경우 예외를 던진다.")
	void duplicateJoinTest() {
		JoinRequest joinRequest1 = new JoinRequest("userId1", "password1");
		JoinRequest joinRequest2 = new JoinRequest("userId1", "password2");
		authService.join(joinRequest1);

		Assertions.assertThatThrownBy(() -> authService.join(joinRequest2))
			.isInstanceOf(JoinException.class);
	}

	@Test
	@DisplayName("로그인 테스트")
	void loginTest() {
		JoinRequest joinRequest = new JoinRequest("userId1", "password1");
		authService.join(joinRequest);

		LoginRequest loginRequest = new LoginRequest("userId1", "password1");
		TokenResponse tokenResponse = authService.login(loginRequest);

		Assertions.assertThat(tokenResponse.getAccessToken()).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken()).isNotNull();
	}

	@Test
	@DisplayName("비밀번호가 틀린 경우 로그인 실패 테스트")
	void loginFailTest1() {
		JoinRequest joinRequest = new JoinRequest("userId1", "password1");
		authService.join(joinRequest);

		LoginRequest loginRequest = new LoginRequest("userId1", "password2");

		Assertions.assertThatThrownBy(() -> authService.login(loginRequest))
			.isInstanceOf(LoginException.class);
	}

	@Test
	@DisplayName("요청 ID로 등록된 회원이 없는 경우 로그인 실패 테스트")
	void loginFailTest2() {
		LoginRequest loginRequest = new LoginRequest("userId", "password");

		Assertions.assertThatThrownBy(() -> authService.login(loginRequest))
			.isInstanceOf(LoginException.class);
	}
}
