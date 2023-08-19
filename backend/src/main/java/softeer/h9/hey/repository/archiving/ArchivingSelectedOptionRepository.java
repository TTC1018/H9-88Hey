package softeer.h9.hey.repository.archiving;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.SelectedOption;

@Repository
@RequiredArgsConstructor
public class ArchivingSelectedOptionRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<SelectedOption> findByArchiving(final Long archivingId) {
		String sql =
			"SELECT selectOption.id, selectOption.name, selectOption.image_url, archiving_selectOption.review, selectOption.additional_price, subOption.name AS sub_option, tag.content AS tag "
				+ "FROM archiving_selectOption "
				+ "LEFT JOIN selectOption ON archiving_selectOption.select_option_id = selectOption.id "
				+ "LEFT JOIN selectOption_subOption ON selectOption_subOption.select_option_id = selectOption.id "
				+ "LEFT JOIN subOption ON selectOption_subOption.sub_option_id = subOption.id "
				+ "LEFT JOIN selectOption_selectedTag ON archiving_selectOption.id = selectOption_selectedTag.archiving_selectOption_id "
				+ "LEFT JOIN tag on selectOption_selectedTag.tag_id = tag.id "
				+ "WHERE archiving_id = :archivingId";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", archivingId);

		return jdbcTemplate.query(sql, params, rowMapper());
	}

	public static RowMapper<SelectedOption> rowMapper() {
		return BeanPropertyRowMapper.newInstance(SelectedOption.class);
	}
}
