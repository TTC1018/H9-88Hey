package softeer.h9.hey.repository.archiving;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.SelectOptionTag;
import softeer.h9.hey.domain.car.Tag;

@Repository
@RequiredArgsConstructor
public class ArchivingTagsRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<SelectOptionTag> findAllByArchivingIdSelectedOptions(final long id) {
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

	public List<String> findBySelectOptionId(final Integer selectOptionId) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "LEFT JOIN selectOption_selectedTag on tag.id = selectOption_selectedTag.tag_id "
			+ "WHERE selectOption_selectedTag.archiving_selectOption_id = :selectOptionId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("selectOptionId", selectOptionId);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	public List<String> findByArchivingId(final Long archivingId) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "LEFT JOIN archiving_selectedTag on tag.id = archiving_selectedTag.tag_id "
			+ "WHERE archiving_selectedTag.archiving_id= :archivingId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", archivingId);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	private List<String> mapToString(List<Tag> tags) {
		List<String> tagStrings = new ArrayList<>();
		for (Tag tag : tags) {
			tagStrings.add(tag.getContent());
		}
		return tagStrings;
	}

	private RowMapper<SelectOptionTag> selectOptionTagRowMapper() {
		return BeanPropertyRowMapper.newInstance(SelectOptionTag.class);
	}

	private RowMapper<String> rowMapper() {
		return (rs, rowNum) -> rs.getString("content");
	}
}
