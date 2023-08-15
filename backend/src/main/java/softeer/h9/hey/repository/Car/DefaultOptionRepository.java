package softeer.h9.hey.repository.car;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.DefaultOption;

@Repository
@RequiredArgsConstructor
public class DefaultOptionRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<DefaultOption> findAllDefaultSubOptionByCarCode(final String carCode) {
		String sql = "SELECT default_option_id AS id, name, description, image_url, category FROM defaultOption LEFT JOIN defaultOption_carTrims ON defaultOption.id = defaultOption_carTrims.default_option_id WHERE car_normal_types_id = :carCode";

		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("carCode", carCode);

		return namedParameterJdbcTemplate.query(sql, params, defaultSubOptionRowMapper());
	}

	private RowMapper<DefaultOption> defaultSubOptionRowMapper() {
		return BeanPropertyRowMapper.newInstance(DefaultOption.class);
	}

}
