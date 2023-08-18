package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.ExteriorColorDto;

@Repository
@RequiredArgsConstructor
public class ArchivingExteriorColorRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ExteriorColorDto findByArchivingId(final Long archivingId) {
		String sql = "SELECT exteriorColor.id, exteriorColor.name, exteriorColor.color_image_url "
			+ "FROM exteriorColor "
			+ "LEFT JOIN archiving_exteriorColor on exteriorColor.id = archiving_exteriorColor.exterior_color_id "
			+ "WHERE archiving_exteriorColor.archiving_id= :archivingId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", archivingId);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}
	private RowMapper<ExteriorColorDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(ExteriorColorDto.class);
	}

}
