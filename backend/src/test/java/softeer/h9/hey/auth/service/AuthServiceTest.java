package softeer.h9.hey.auth.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;

@DisplayName("인증 관련 서비스 기능 테스트")
class AuthServiceTest {

	private final AuthService authService = new AuthService();

	@Test
	@DisplayName("아이디와 비밀번호를 전달하여 회원가입을 한다.")
	void joinTest() {
		JoinRequest joinRequest = new JoinRequest("userId", "password");

		TokenResponse tokenResponse = authService.join(joinRequest);

		Assertions.assertThat(tokenResponse.getAccessToken).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken).isNotNull();
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
		JoinRequest joinRequest1 = new JoinRequest("userId1", "password1");
		authService.join(joinRequest1);

		LoginRequest loginRequest = new LoginRequest("userId1", "password1");
		TokenResponse tokenResponse = authService.login(joinRequest1);

		Assertions.assertThat(tokenResponse.getAccessToken).isNotNull();
		Assertions.assertThat(tokenResponse.getRefreshToken).isNotNull();
	}

	@Test
	@DisplayName("로그인 실패 테스트")
	void loginFailTest() {
		JoinRequest joinRequest1 = new JoinRequest("userId1", "password1");
		authService.join(joinRequest1);

		LoginRequest loginRequest = new LoginRequest("userId1", "password2");

		Assertions.assertThatThrownBy(() -> authService.login(loginRequest))
			.isInstanceOf(LoginException.class);
	}
}
