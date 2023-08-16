package softeer.h9.hey.auth.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.ExpiredJwtException;

@DisplayName("JWT 토큰 프로바이더 테스트")
class JwtTokenProviderTest {

	private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("testKey", 300, 300);

	@Test
	@DisplayName("특정 userID를 담은 JWT 토큰을 생성한다.")
	void generateTokenTest() {
		String userId = "userId";

		String jwt = jwtTokenProvider.generateAccessToken(userId);

		Assertions.assertEquals(userId, jwtTokenProvider.getSubjectFromToken(jwt));
	}

	@Test
	@DisplayName("AccessToken의 만료 시간이 지나면 유요하지 않은 토큰으로 판단한다.")
	void validateTokenTest() throws InterruptedException {
		String userId = "userId";

		String jwt = jwtTokenProvider.generateAccessToken(userId);
		Thread.sleep(500);

		assertThatThrownBy(() -> jwtTokenProvider.validateToken(jwt))
			.isInstanceOf(ExpiredJwtException.class);
	}
}
