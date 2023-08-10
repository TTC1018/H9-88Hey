package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.Trim;
import softeer.h9.hey.domain.car.TrimFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TrimRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Trim> findTrimsByModelId(int modelId) {
        String sql = "SELECT * FROM trim LEFT JOIN trim_feature tf ON trim.id = tf.trim_id WHERE trim.model_id = :model_id";

        Map<String, Object> params = Map.of("model_id", modelId);

        return fetchTrimsWithFeatures(sql, params);
    }

    private ArrayList<Trim> fetchTrimsWithFeatures(String sql, Map<String, Object> params) {
        HashMap<Integer, Trim> trimMap = new HashMap<>();

        namedParameterJdbcTemplate.query(sql, params, rs -> {
            int trimId = rs.getInt("trim.id");

            Trim trim = trimMap.getOrDefault(
                    trimId,
                    new Trim(trimId, rs.getString("trim.name"), rs.getInt("price"))
            );

            TrimFeature trimFeature = new TrimFeature();
            trimFeature.setName(rs.getString("tf.name"));
            trimFeature.setImageUrl(rs.getString("tf.image_url"));

            trim.addTrimFeature(trimFeature);

            trimMap.putIfAbsent(trimId, trim);
        });

        return new ArrayList<>(trimMap.values());
    }
}
