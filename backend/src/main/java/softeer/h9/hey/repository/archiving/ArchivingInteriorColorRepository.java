package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.InteriorColorDto;

@Repository
@RequiredArgsConstructor
public class ArchivingInteriorColorRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public InteriorColorDto findByArchivingId(final Long archivingId) {
		String sql = "SELECT interiorColor.id, interiorColor.name, interiorColor.color_image_url "
			+ "FROM interiorColor "
			+ "LEFT JOIN archiving_interiorColor on interiorColor.id = archiving_interiorColor.interior_color_id "
			+ "WHERE archiving_interiorColor.archiving_id= :archivingId ";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("archivingId", archivingId);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}
	private RowMapper<InteriorColorDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(InteriorColorDto.class);
	}

}
