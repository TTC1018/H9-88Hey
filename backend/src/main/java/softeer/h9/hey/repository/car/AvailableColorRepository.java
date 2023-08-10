package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.AvailableColor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AvailableColorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<AvailableColor> findAllByTrimId(final int trimId) {
        String sql = "SELECT * FROM exteriorColor_interiorColor WHERE trim_id = :trim_id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("trim_id", trimId);

        return jdbcTemplate.query(sql, param, availableColorRowMapper());
    }

    private RowMapper<AvailableColor> availableColorRowMapper() {
        return BeanPropertyRowMapper.newInstance(AvailableColor.class);
    }
}
