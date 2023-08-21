package softeer.h9.hey.repository.car;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.SelectOptionTag;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TagRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<SelectOptionTag> findAllByArchivingId(final long id) {
		String sql = "SELECT selectOption.id AS select_option_id, tag.content AS tag\n"
			+ "FROM archiving\n"
			+ "LEFT JOIN archiving_selectOption ON archiving.id = archiving_selectOption.archiving_id\n"
			+ "LEFT JOIN selectOption ON archiving_selectOption.select_option_id = selectOption.id\n"
			+ "LEFT JOIN selectOption_selectedTag ON archiving_selectOption.id = selectOption_selectedTag.archiving_selectOption_id\n"
			+ "LEFT JOIN tag ON selectOption_selectedTag.tag_id = tag.id\n"
			+ "WHERE archiving.id = :archivingId";
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", id);

		return jdbcTemplate.query(sql, params, selectOptionTagRowMapper());
	}

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

	public List<String> findTopBySelectOptionId(final String id, final int limit) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "         LEFT JOIN selectOption_selectedTag on tag.id = selectOption_selectedTag.tag_id "
			+ "         LEFT JOIN archiving_selectOption "
			+ "                   on selectOption_selectedTag.archiving_selectOption_id = archiving_selectOption.id "
			+ "WHERE select_option_id = :selectOptionId "
			+ "GROUP BY tag.id "
			+ "ORDER BY COUNT(tag.id) DESC "
			+ "LIMIT :limit";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("selectOptionId", id)
			.addValue("limit", limit);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	private RowMapper<SelectOptionTag> selectOptionTagRowMapper() {
		return BeanPropertyRowMapper.newInstance(SelectOptionTag.class);
	}

	private RowMapper<String> rowMapper() {
		return (rs, rowNum) -> rs.getString("content");
	}

}
