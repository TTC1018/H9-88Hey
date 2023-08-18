package softeer.h9.hey.repository.myChiving;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;

@SpringBootTest
class MyChivingRepositoryTest {

	@Autowired
	MyChivingRepository myChivingRepository;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	@DisplayName("마이카이빙에 최초로 임시저장을 한 경우 데이터베이스에 데이터를 임시저장하고 마이카이빙 아이디를 반환해야 한다.")
	@Transactional
	void tempSave() {
		Long id = null;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP","DUP","VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId, engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.fromMyChivingTempSaveRequest(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(false);

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", myChivingId);

		String myChivingSql = "SELECT * FROM myArchiving WHERE id = :id";

		namedParameterJdbcTemplate.query(myChivingSql, sqlParameterSource, result -> {
			assertThat(result.getInt("engine_id")).isEqualTo(1);
			assertThat(result.getInt("trim_id")).isEqualTo(1);
			assertThat(result.getInt("body_type_id")).isEqualTo(1);
			assertThat(result.getInt("wheel_type_id")).isEqualTo(1);
			assertThat(result.getInt("exterior_color_id")).isEqualTo(1);
			assertThat(result.getInt("interior_color_id")).isEqualTo(1);
		});

		String selectOptionSql = "SELECT * FROM myArchiving_selectOption WHERE id = :id";

		namedParameterJdbcTemplate.query(selectOptionSql, sqlParameterSource, result -> {
			assertThat(result.getString("select_option_id")).isIn(selectOptionIdList);
		});
	}
}
