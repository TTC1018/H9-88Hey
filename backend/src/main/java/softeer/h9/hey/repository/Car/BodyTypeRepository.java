package softeer.h9.hey.repository.Car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.BodyType;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BodyTypeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<BodyType> findBodyTypesByModelId(final int modelId) {
        String sql = "SELECT * FROM bodyType WHERE model_id = :model_id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("model_id", modelId);
        return jdbcTemplate.query(sql, param, bodyTypeRowMapper());
    }

    private RowMapper<BodyType> bodyTypeRowMapper() {
        return BeanPropertyRowMapper.newInstance(BodyType.class);
    }
}
