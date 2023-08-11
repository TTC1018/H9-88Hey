package softeer.h9.hey.repository.car;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.ModelImage;
import softeer.h9.hey.domain.car.WheelDrive;

@Repository
@RequiredArgsConstructor
public class WheelDriveRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<WheelDrive> findAllByModelId(final int modelId) {

	}

	private RowMapper<WheelDrive> wheelDriveRow한Mapper() {
		return BeanPropertyRowMapper.newInstance(WheelDrive.class);
	}
}
