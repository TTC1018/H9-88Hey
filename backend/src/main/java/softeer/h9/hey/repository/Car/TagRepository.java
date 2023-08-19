package softeer.h9.hey.repository.car;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TagRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<String> findTopByInteriorColorId(final int id, final int limit) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "         LEFT JOIN interiorColor_selectedTag on tag.id = interiorColor_selectedTag.tag_id "
			+ "         LEFT JOIN archiving_interiorColor "
			+ "                   on interiorColor_selectedTag.archiving_interiorColor_id = archiving_interiorColor.id "
			+ "WHERE interior_color_id = :interiorColorId "
			+ "GROUP BY tag.id "
			+ "ORDER BY COUNT(tag.id) DESC "
			+ "LIMIT :limit";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("interiorColorId", id)
			.addValue("limit", limit);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	public List<String> findTopByExteriorColorId(final int id, final int limit) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "         LEFT JOIN exteriorColor_selectedTag on tag.id = exteriorColor_selectedTag.tag_id "
			+ "         LEFT JOIN archiving_exteriorColor "
			+ "                   on exteriorColor_selectedTag.archiving_exteriorColor_id = archiving_exteriorColor.id "
			+ "WHERE exterior_color_id = :exteriorColorId "
			+ "GROUP BY tag.id "
			+ "ORDER BY COUNT(tag.id) DESC "
			+ "LIMIT :limit";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("exteriorColorId", id)
			.addValue("limit", limit);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	private RowMapper<String> rowMapper() {
		return (rs, rowNum) -> rs.getString("content");
	}

}
