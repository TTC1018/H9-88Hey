package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.dto.archiving.TrimDto;

@SpringBootTest
@DisplayName("아카이빙 Trim Repository 테스트")
class ArchivingTrimRepositoryTest {

	@Autowired
	private ArchivingTrimRepository repository;

	@DisplayName("아카이빙 car code기반으로 Trim을 조회한다.")
	@Test
	void findByCarCode() {
		String carCode = "LXJJ7MCT5";

		TrimDto dto = repository.findByCarCode(carCode);

		assertAll(
			() -> assertEquals(4, dto.getId()),
			() -> assertEquals("Calligraphy", dto.getName()),
			() -> assertEquals(51060000, dto.getPrice())
		);
	}
}
