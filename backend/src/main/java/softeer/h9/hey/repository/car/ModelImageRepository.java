package softeer.h9.hey.repository.car;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.ModelImage;

@Repository
@RequiredArgsConstructor
public class ModelImageRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<ModelImage> findAllByModelId(final int modelId) {
		String sql = "select * from `modelImage` where model_id = :modelId";

		Map<String, Object> params = Map.of("modelId", modelId);

		return namedParameterJdbcTemplate.query(sql, params, modelImageRowMapper());
	}

	private RowMapper<ModelImage> modelImageRowMapper() {
		return BeanPropertyRowMapper.newInstance(ModelImage.class);
	}
}
