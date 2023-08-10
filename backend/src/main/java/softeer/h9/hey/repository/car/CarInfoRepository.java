package softeer.h9.hey.repository.car;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import softeer.h9.hey.domain.car.CarInfo;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CarInfoRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<CarInfo> findBy(final int trimId, final int engineId, final int bodyTypeId, final int wheelDriveId) {
        String sql = "select id as car_code, trim_id, engine_id, body_type_id, wheel_type_id from `carNormalTypes` " +
                "where trim_id = :trimId AND engine_id = :engineId AND body_type_id = :bodyTypeId AND wheel_type_id = :wheelDriveId";
        System.out.println(sql);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("trimId", trimId)
                .addValue("engineId", engineId)
                .addValue("bodyTypeId", bodyTypeId)
                .addValue("wheelDriveId", wheelDriveId);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, carCodeRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<CarInfo> carCodeRowMapper() {
        return BeanPropertyRowMapper.newInstance(CarInfo.class);
    }
}
