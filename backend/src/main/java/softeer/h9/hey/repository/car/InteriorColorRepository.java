package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.InteriorColor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InteriorColorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<InteriorColor> findAllByTrimId(final int trimId) {
        String sql = "SELECT DISTINCT i.id AS id, name, color_image_url, interior_color_id AS car_image_url, additional_price, ei.trim_id " +
                "FROM interiorColor i,exteriorColor_interiorColor ei " +
                "WHERE ei.trim_id = :trim_id AND ei.interior_color_id = i.id " +
                "ORDER BY i.id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("trim_id", trimId);

        return jdbcTemplate.query(sql, param, interiorColorRowMapper());
    }

    private RowMapper<InteriorColor> interiorColorRowMapper() {
        return BeanPropertyRowMapper.newInstance(InteriorColor.class);
    }
}
