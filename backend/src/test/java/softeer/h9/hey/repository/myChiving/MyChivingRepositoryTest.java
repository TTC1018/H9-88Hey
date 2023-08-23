package softeer.h9.hey.repository.myChiving;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import softeer.h9.hey.dto.myChiving.MyChivingDto;
import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionFetchDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.exception.myChiving.InValidAccessException;

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
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId,
			engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingTempSaveRequest);
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

		String selectOptionSql = "SELECT * FROM myArchiving_selectOption WHERE myArchiving_id = :id";

		namedParameterJdbcTemplate.query(selectOptionSql, sqlParameterSource, result -> {
			assertThat(result.getString("select_option_id")).isIn(selectOptionIdList);
		});
	}

	@Test
	@DisplayName("유저 아이디와 limit, offset 정보를 개수와 순서에 맞게 유저의 마이카이빙 테이블 저장 정보를 가져와야 한다.")
	@Transactional
	void findMyChiving() {
		Long id = null;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId,
			engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(0);
		myChivingSaveDto.setSubmitted(false);

		List<Long> myChivingIdList = List.of(
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto));

		Integer userId = 0;
		Integer limit = 4;
		Integer offset = 0;

		List<MyChivingDto> myChivingDtoList = myChivingRepository.findMyChivingsByUserIdLimitAndOffset(userId, limit,
			offset);

		assertThat(myChivingDtoList).hasSize(4);
		//필수값 체크
		for (MyChivingDto myChivingDto : myChivingDtoList) {
			assertThat(myChivingDto.getMyChivingId()).isIn(myChivingIdList);
			assertThat(myChivingDto.getLastModifiedDate()).isNotNull();
			assertThat(myChivingDto.getTotalPrice()).isNotNull();
			assertThat(myChivingDto.getModel()).isNotNull();
			assertThat(myChivingDto.getTrim()).isNotNull();
			assertThat(myChivingDto.getIsSaved()).isNotNull();
		}
	}

	@Test
	@DisplayName("불러온 마이카이빙 목록을 통해 해당 마이카이빙에서 선택한 선택옵션과 하위옵션 정보를 가져와야 한다.")
	@Transactional
	void findOptionDataByMyChiving() {
		Long id = null;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId,
			engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(false);

		List<Long> myChivingIdList = List.of(myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto),
			myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto));

		List<MyChivingSelectOptionFetchDto> optionDataByMyChivingIdList = myChivingRepository.findOptionDataByMyChivingIdList(
			myChivingIdList);

		for (MyChivingSelectOptionFetchDto myChivingSelectOptionFetchDto : optionDataByMyChivingIdList) {
			assertThat(myChivingSelectOptionFetchDto.getMyChivingId()).isNotNull();
			assertThat(myChivingSelectOptionFetchDto.getSelectOptionId()).isNotNull();
			assertThat(myChivingSelectOptionFetchDto.getSelectOptionName()).isNotNull();
			assertThat(myChivingSelectOptionFetchDto.getImageUrl()).isNotNull();
			assertThat(myChivingSelectOptionFetchDto.getAdditionalPrice()).isNotNull();
			assertThat(myChivingSelectOptionFetchDto.getSubOptionName()).isNotNull();
		}
	}

	@Test
	@DisplayName("유저에게 존재하지 않는 마이카이빙 아이디로 삭제하려 할 때 InvalidAccessException을 반환해야 한다.")
	@Transactional
	void invalidAccessExceptionTest() {
		//존재하지 않는 아이디 삽입
		int userId = 1;
		long invalidMyChivingId = 1234L;

		assertThatThrownBy(
			() -> myChivingRepository.deleteMyChivingByMyChivingAndUserId(userId, invalidMyChivingId))
			.isInstanceOf(InValidAccessException.class);

	}

	@Test
	@DisplayName("임의로 새로운 마이카이빙을 추가하고 삭제할 때 예외없이 삭제가 되어야 한다.")
	@Transactional
	void deleteMyChivingTest() {
		Long id = null;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId,
			engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(false);

		long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		//만약 없는 값을 삭제하려 한다면 예외가 터지기 때문에 예외가 없다면 제대로 실행됨
		myChivingRepository.deleteMyChivingByMyChivingAndUserId(1, myChivingId);
	}
}
