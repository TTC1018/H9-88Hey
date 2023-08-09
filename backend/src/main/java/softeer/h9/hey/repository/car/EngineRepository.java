package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.Engine;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EngineRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Engine> findByModelId(int modelId) {
        String sql = "select * from `engine` where model_id = :modelId";

        Map<String, Object> params = Map.of("modelId", modelId);

        return namedParameterJdbcTemplate.query(sql, params, engineRowMapper());
    }

    private RowMapper<Engine> engineRowMapper() {
        return BeanPropertyRowMapper.newInstance(Engine.class);
    }
}
