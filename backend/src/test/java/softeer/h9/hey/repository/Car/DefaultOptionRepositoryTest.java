package softeer.h9.hey.repository.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.DefaultOption;

@SpringBootTest
@DisplayName("DefaultOptionRepository 테스트")
class DefaultOptionRepositoryTest {

	@Autowired
	private DefaultOptionRepository defaultOptionRepository;

	@Test
	@DisplayName("carCode를 통해 기본 옵션들을 조회한다.")
	void findAllDefaultSubOptionByCarCode() {
		String carCode = "LXJJ8MST5";

		List<DefaultOption> defaultOptions = defaultOptionRepository.findAllDefaultSubOptionByCarCode(carCode);

		for (DefaultOption defaultOption : defaultOptions) {
			assertNotNull(defaultOption.getId());
			assertNotNull(defaultOption.getName());
			assertNotNull(defaultOption.getCategory());
			assertNotNull(defaultOption.getDescription());
			assertNotNull(defaultOption.getImageUrl());
		}

	}
}
