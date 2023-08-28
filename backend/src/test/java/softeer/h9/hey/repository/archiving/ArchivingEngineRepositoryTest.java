package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.dto.archiving.EngineDto;

@SpringBootTest
@DisplayName("아카이빙 Engine Repository 테스트")
class ArchivingEngineRepositoryTest {

	@Autowired
	private ArchivingEngineRepository repository;

	@DisplayName("아카이빙 car code기반으로 Engine을 조회한다.")
	@Test
	void findByCarCode() {
		String carCode = "LXJJ7MCT5";

		EngineDto dto = repository.findByCarCode(carCode);

		assertAll(
			() -> assertEquals(2, dto.getId()),
			() -> assertEquals("가솔린 3.8", dto.getName()),
			() -> assertEquals(0, dto.getAdditionalPrice())
		);
	}
}
