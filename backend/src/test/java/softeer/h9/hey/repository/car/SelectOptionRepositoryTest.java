package softeer.h9.hey.repository.car;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.SelectOptionCategory;
import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.dto.car.DisabledOptionIdDto;

@SpringBootTest
@DisplayName("선택 옵션 저장소 테스트")
public class SelectOptionRepositoryTest {

	@Autowired
	SelectOptionRepository repository;

	@DisplayName("특정 자동차에 적용할 수 있는 선택 옵션을 조회한다.")
	@Test
	void findByCarCodeTest() {
		String carCode = "LXJJ8MST5";

		List<SelectOption> selectOptions = repository.findSelectOptionsByCarCode(carCode);

		assertThat(selectOptions).hasSize(6);
		for (SelectOption selectOption : selectOptions) {
			assertThat(selectOption.getSelectOptionCategory()).isEqualTo(SelectOptionCategory.SELECT_OPTION);
		}
	}

	@DisplayName("특정 자동차에 적용할 수 있는 N Performance 옵션을 조회한다.")
	@Test
	void findNPerformanceOptionTest() {
		String carCode = "LXJJ8MST5";

		List<SelectOption> selectOptions = repository.findNPerformanceByCarCode(carCode);

		assertThat(selectOptions).hasSize(3);
		for (SelectOption selectOption : selectOptions) {
			assertThat(selectOption.getSelectOptionCategory()).isEqualTo(SelectOptionCategory.N_PERFORMANCE);
		}
	}

	@Test
	@DisplayName("특정 자동차에 적용할 수 있는 H Genuine Accessory 옵션을 조회한다.")
	void findHGenuineOptionTest() {
		String carCode = "LXJJ8MBA5";

		List<SelectOption> selectOptions = repository.findHGenuineAccessoriesByCarCode(carCode);

		assertThat(selectOptions).hasSize(6);

		for (SelectOption selectOption : selectOptions) {
			assertThat(selectOption.getSelectOptionCategory()).isEqualTo(SelectOptionCategory.H_GENUINE);
		}
	}

	@Test
	@DisplayName("선택한 옵션들에 대한 하위옵션 id 값들을 중복을 제거하여 조회한다.")
	void findDisabledOptionIdsBySelectOptionIdsTest() {
		List<String> selectOptionIds = List.of("LST", "US1");

		List<DisabledOptionIdDto> disabledOptionIdDtos = repository.findDisabledOptionIdsBySelectOptionIds(selectOptionIds);

		assertThat(disabledOptionIdDtos).hasSize(1);
	}
}
