package softeer.h9.hey.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer.h9.hey.auth.exception.InvalidTokenException;

@DisplayName("JWT 토큰 프로바이더 테스트")
class JwtTokenProviderTest {
	public static final String SECRET_KEY = "testKey_1234567890_test_12341234";
	public static final int ACCESS_TOKEN_EXPIRED_TIME_MS = 1000;
	public static final int REFRESH_TOKEN_EXPIRED_TIME_MS = 2000;
	public static final String USER_NAME = "userName";
	public static final String SUBJECT = "sub";

	private final JwtTokenProvider jwtTokenProvider =
		new JwtTokenProvider(SECRET_KEY, ACCESS_TOKEN_EXPIRED_TIME_MS, REFRESH_TOKEN_EXPIRED_TIME_MS);

	@Test
	@DisplayName("user의 PK와 name을 담은 JWT Access, Refresh 토큰을 생성한다.")
	void generateTokenTest() {
		int userId = 1;
		String userName = "userName123";
		Map<String, Object> claims = Map.of(USER_NAME, userName);

		String accessToken = jwtTokenProvider.generateAccessToken(1, claims);
		String refreshToken = jwtTokenProvider.generateRefreshToken(1, claims);

		Map<String, Object> accessClaims = jwtTokenProvider.getClaimsFromToken(accessToken);
		Map<String, Object> refreshClaims = jwtTokenProvider.getClaimsFromToken(refreshToken);

		assertEquals(String.valueOf(userId), accessClaims.get(SUBJECT));
		assertEquals(userName, accessClaims.get(USER_NAME));
		assertEquals(String.valueOf(userId), refreshClaims.get(SUBJECT));
		assertEquals(userName, refreshClaims.get(USER_NAME));
	}

	@Test
	@DisplayName("AccessToken의 만료 시간이 지나면 유효하지 않은 토큰으로 판단한다.")
	void validateExpiredTimeTest() throws InterruptedException {
		int userId = 1;
		Map<String, Object> claims = Map.of(USER_NAME, "userName");

		String jwt = jwtTokenProvider.generateAccessToken(userId, claims);
		Thread.sleep(1500);

		assertThatThrownBy(() -> jwtTokenProvider.getClaimsFromToken(jwt))
			.isInstanceOf(InvalidTokenException.class);
	}

	@Test
	@DisplayName("토큰이 조작되면 유효하지 않은 토큰으로 판단한다.")
	void validateForgeryTokenTest() {
		int userId = 1;
		Map<String, Object> claims = Map.of(USER_NAME, "userName");

		String jwt = jwtTokenProvider.generateAccessToken(userId, claims) + "abc";

		assertThatThrownBy(() -> jwtTokenProvider.getClaimsFromToken(jwt))
			.isInstanceOf(InvalidTokenException.class);
	}

	@Test
	@DisplayName("유효한 토큰으로부터 사용자의 이름을 가져온다.")
	void getValidatedUserNameFromToken() {
		int userId = 1;
		String expectedUserName = "name";
		Map<String, Object> claims = Map.of(USER_NAME, expectedUserName);

		String jwt = jwtTokenProvider.generateAccessToken(userId, claims);

		String actualUserName = jwtTokenProvider.getUserNameFromToken(jwt);

		Assertions.assertEquals(expectedUserName, actualUserName);
	}
}
