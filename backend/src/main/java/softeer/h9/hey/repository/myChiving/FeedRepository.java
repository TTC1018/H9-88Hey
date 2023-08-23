package softeer.h9.hey.repository.myChiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.Feed;

@Repository
@RequiredArgsConstructor
public class FeedRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<Feed> hasBookmark(final int userId, final long feedId) {
		String sql = "SELECT feed.archiving_id AS feed_id, feed.user_id AS user_id, feed.is_marked AS is_marked\n"
			+ "FROM feed\n"
			+ "WHERE user_id = :userId AND archiving_id = :feedId";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("userId", userId)
			.addValue("feedId", feedId);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	public int save(final int userId, final long feedId) {
		String sql = "INSERT INTO feed (user_id, archiving_id, is_marked) VALUES (:userId, :feedId, TRUE) ";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("userId", userId)
			.addValue("feedId", feedId);

		return jdbcTemplate.update(sql, params);
	}

	private RowMapper<Feed> rowMapper() {
		return BeanPropertyRowMapper.newInstance(Feed.class);
	}
}
