package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.TrimDto;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingTrimRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public TrimDto findByCarCode(final String carCode) {
		String sql = "SELECT trim.id, trim.name, trim.price "
			+ "FROM trim "
			+ "LEFT JOIN carNormalTypes on trim.id = carNormalTypes.body_type_id "
			+ "WHERE carNormalTypes.id = :carCode";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}

	private RowMapper<TrimDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(TrimDto.class);
	}
}
