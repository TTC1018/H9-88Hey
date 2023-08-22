package softeer.h9.hey.auth.repository;

import java.util.List;

import softeer.h9.hey.auth.domain.RefreshTokenEntity;

public interface RefreshTokenRepository {
	void save(RefreshTokenEntity refreshTokenEntity);

	List<RefreshTokenEntity> findByUserId(int userId);

	void deleteById(int refreshTokenEntityId);

	void deleteBeforeCurrentTime();
}
