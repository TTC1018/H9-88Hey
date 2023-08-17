package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.WheelDriveDto;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingWheelDriveRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public WheelDriveDto findByCarCode(final String carCode) {
		String sql = "SELECT wheelType.id, wheelType.name, wheelType.additional_price "
			+ "FROM wheelType "
			+ "LEFT JOIN carNormalTypes on wheelType.id = carNormalTypes.body_type_id "
			+ "WHERE carNormalTypes.id = :carCode";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}

	private RowMapper<WheelDriveDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(WheelDriveDto.class);
	}
}
