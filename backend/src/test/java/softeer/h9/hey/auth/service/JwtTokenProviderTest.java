package softeer.h9.hey.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer.h9.hey.auth.exception.InvalidTokenException;

@DisplayName("JWT 토큰 프로바이더 테스트")
class JwtTokenProviderTest {
	public static final String SECRET_KEY = "testKey_1234567890_test_12341234";
	public static final int ACCESS_TOKEN_EXPIRED_TIME_MS = 1000;
	public static final int REFRESH_TOKEN_EXPIRED_TIME_MS = 2000;

	private final JwtTokenProvider jwtTokenProvider =
		new JwtTokenProvider(SECRET_KEY, ACCESS_TOKEN_EXPIRED_TIME_MS, REFRESH_TOKEN_EXPIRED_TIME_MS);

	@Test
	@DisplayName("특정 userID를 담은 JWT Access, Refresh 토큰을 생성한다.")
	void generateTokenTest() {
		String userId = "userId";

		String accessToken = jwtTokenProvider.generateAccessToken(userId);
		String refreshToken = jwtTokenProvider.generateRefreshToken(userId);

		assertEquals(userId, jwtTokenProvider.getSubjectFromToken(accessToken));
		assertEquals(userId, jwtTokenProvider.getSubjectFromToken(refreshToken));
	}

	@Test
	@DisplayName("AccessToken의 만료 시간이 지나면 유효하지 않은 토큰으로 판단한다.")
	void validateExpiredTimeTest() throws InterruptedException {
		String userId = "userId";

		String jwt = jwtTokenProvider.generateAccessToken(userId);
		Thread.sleep(1500);

		assertThatThrownBy(() -> jwtTokenProvider.getSubjectFromToken(jwt))
			.isInstanceOf(InvalidTokenException.class);
	}

	@Test
	@DisplayName("토큰이 조작되면 유효하지 않은 토큰으로 판단한다.")
	void validateForgeryTokenTest() {
		String userId = "userId";

		String jwt = jwtTokenProvider.generateAccessToken(userId) + "abc";

		assertThatThrownBy(() -> jwtTokenProvider.getSubjectFromToken(jwt))
			.isInstanceOf(InvalidTokenException.class);
	}
}
