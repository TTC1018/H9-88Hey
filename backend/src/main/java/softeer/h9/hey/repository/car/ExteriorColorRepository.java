package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.ExteriorColor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExteriorColorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<ExteriorColor> findAllByTrimId(final int trimId) {
        String sql = "SELECT DISTINCT e.id AS id, name, color_image_url, exterior_image_url AS car_image_path, additional_price, ei.trim_id " +
                "FROM exteriorColor e,exteriorColor_interiorColor ei " +
                "WHERE ei.trim_id = :trim_id AND ei.exterior_color_id = e.id " +
                "ORDER BY e.id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("trim_id", trimId);

        return jdbcTemplate.query(sql, param, exteriorColorRowMapper());
    }

    private RowMapper<ExteriorColor> exteriorColorRowMapper() {
        return BeanPropertyRowMapper.newInstance(ExteriorColor.class);
    }
}
