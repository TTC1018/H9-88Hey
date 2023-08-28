package softeer.h9.hey.repository.car;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AvailableColorRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public List<Integer> findAllByTrimIdAndExteriorColor(final int trimId, final int exteriorColorId) {
		String sql = "SELECT interior_color_id FROM exteriorColor_interiorColor WHERE trim_id = :trim_id AND exterior_color_id = :exterior_color_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("trim_id", trimId)
			.addValue("exterior_color_id", exteriorColorId);

		return jdbcTemplate.queryForList(sql, param, Integer.class);
	}
}
