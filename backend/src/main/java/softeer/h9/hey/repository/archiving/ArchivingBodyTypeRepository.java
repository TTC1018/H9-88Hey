package softeer.h9.hey.repository.archiving;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.archiving.BodyTypeDto;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingBodyTypeRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public BodyTypeDto findByCarCode(final String carCode) {
		String sql = "SELECT bodyType.id, bodyType.name, bodyType.additional_price "
			+ "FROM bodyType "
			+ "LEFT JOIN carNormalTypes on bodyType.id = carNormalTypes.body_type_id "
			+ "WHERE carNormalTypes.id = :carCode";

		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return jdbcTemplate.queryForObject(sql, params, rowMapper());
	}

	private RowMapper<BodyTypeDto> rowMapper() {
		return BeanPropertyRowMapper.newInstance(BodyTypeDto.class);
	}
}
