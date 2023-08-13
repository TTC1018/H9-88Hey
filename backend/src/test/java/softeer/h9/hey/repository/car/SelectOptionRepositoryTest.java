package softeer.h9.hey.repository.car;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.SelectOptionCategory;
import softeer.h9.hey.domain.car.SelectOption;

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
}
