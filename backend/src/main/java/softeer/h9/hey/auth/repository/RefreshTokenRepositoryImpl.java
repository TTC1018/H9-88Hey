package softeer.h9.hey.auth.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.domain.RefreshTokenEntity;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void save(RefreshTokenEntity refreshTokenEntity) {
		String sql = "INSERT INTO `refreshToken`(user_id, refreshToken) VALUES (:userId, :refreshToken)";

		MapSqlParameterSource param = new MapSqlParameterSource()
			.addValue("userId", refreshTokenEntity.getUserId())
			.addValue("refreshToken", refreshTokenEntity.getRefreshToken());

		namedParameterJdbcTemplate.update(sql, param);
	}

	@Override
	public List<RefreshTokenEntity> findByUserId(int userId) {
		String sql = "SELECT * FROM refreshToken WHERE user_id = :userId";

		MapSqlParameterSource param = new MapSqlParameterSource()
			.addValue("userId", userId);

		return namedParameterJdbcTemplate.query(sql, param, refreshTokenEntityRowMapper());
	}

	@Override
	public void deleteById(int refreshTokenEntityId) {
		String sql = "delete from refreshToken where id = :refreshTokenId";

		MapSqlParameterSource param = new MapSqlParameterSource()
			.addValue("refreshTokenId", refreshTokenEntityId);

		namedParameterJdbcTemplate.update(sql, param);
	}

	private RowMapper<RefreshTokenEntity> refreshTokenEntityRowMapper() {
		return BeanPropertyRowMapper.newInstance(RefreshTokenEntity.class);
	}
}
