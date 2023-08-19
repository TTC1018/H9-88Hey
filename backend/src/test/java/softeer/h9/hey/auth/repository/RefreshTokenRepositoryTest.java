package softeer.h9.hey.auth.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import softeer.h9.hey.auth.domain.RefreshTokenEntity;

@SpringBootTest
@Transactional
@DisplayName("Refresh Token 테스트")
class RefreshTokenRepositoryTest {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Test
	@DisplayName("RefreshToken를 저장 및 조회한다.")
	void createRefreshTokenTest() {
		int userId = 1;
		String refreshToken = "jwt1234";
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(userId, refreshToken);

		refreshTokenRepository.save(refreshTokenEntity);
		List<RefreshTokenEntity> findRefreshTokenEntities = refreshTokenRepository.findByUserId(userId);

		RefreshTokenEntity findRefreshToken = findRefreshTokenEntities.get(0);
		Assertions.assertEquals(userId, findRefreshToken.getUserId());
		Assertions.assertEquals(refreshToken, findRefreshToken.getRefreshToken());
	}
}
