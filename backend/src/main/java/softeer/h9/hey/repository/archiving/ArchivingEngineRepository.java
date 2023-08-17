package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.EngineDto;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingEngineRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public EngineDto findByCarCode(final String carCode) {
		String sql = "SELECT `engine`.id, `engine`.name, `engine`.additional_price "
			+ "FROM `engine` "
			+ "LEFT JOIN carNormalTypes on `engine`.id = carNormalTypes.body_type_id "
			+ "WHERE carNormalTypes.id = :carCode";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}

	private RowMapper<EngineDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(EngineDto.class);
	}
}
