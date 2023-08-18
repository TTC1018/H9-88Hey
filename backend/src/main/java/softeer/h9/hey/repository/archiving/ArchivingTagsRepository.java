package softeer.h9.hey.repository.archiving;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.Tag;
import softeer.h9.hey.dto.archiving.TagsDto;

@Repository
@RequiredArgsConstructor
public class ArchivingTagsRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public TagsDto findBySelectOptionId(final Integer selectOptionId) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "LEFT JOIN selectOption_selectedTag on tag.id = selectOption_selectedTag.tag_id "
			+ "WHERE selectOption_selectedTag.archiving_selectOption_id = :selectOptionId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("selectOptionId", selectOptionId);

		List<Tag> tags = jdbcTemplate.query(sql, params, rowMapper());
		return TagsDto.of(mapToString(tags));
	}

	public TagsDto findByArchivingId(final Long archivingId) {
		String sql = "SELECT tag.content "
			+ "FROM tag "
			+ "LEFT JOIN archiving_selectedTag on tag.id = archiving_selectedTag.tag_id "
			+ "WHERE archiving_selectedTag.archiving_id= :archivingId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", archivingId);

		List<Tag> tags = jdbcTemplate.query(sql, params, rowMapper());
		return TagsDto.of(mapToString(tags));
	}

	private List<String> mapToString(List<Tag> tags) {
		List<String> tagStrings = new ArrayList<>();
		for (Tag tag : tags) {
			tagStrings.add(tag.getContent());
		}
		return tagStrings;
	}

	private RowMapper<Tag> rowMapper() {
		return BeanPropertyRowMapper.newInstance(Tag.class);
	}
}
