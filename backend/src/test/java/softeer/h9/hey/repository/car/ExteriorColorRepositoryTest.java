package softeer.h9.hey.repository.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.ExteriorColor;

@SpringBootTest
@DisplayName("ExteriorColorRepository Test")
class ExteriorColorRepositoryTest {

	@Autowired
	private ExteriorColorRepository exteriorColorRepository;

	@Test
	@DisplayName("DB에 저장된 특정 트림의 외장 색상들을 조회한다.")
	void findAllByTrimId() {
		int trimId = 1;

		List<ExteriorColor> exteriorColors = exteriorColorRepository.findAllByTrimId(trimId);

		assertAll(
			() -> assertNotNull(exteriorColors),
			() -> assertEquals(6, exteriorColors.size())
		);
	}
}
