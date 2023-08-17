package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.dto.archiving.BodyTypeDto;

@SpringBootTest
@DisplayName("아카이빙 BodyType Repository 테스트")
class ArchivingBodyTypeRepositoryTest {

	@Autowired
	private ArchivingBodyTypeRepository repository;

	@DisplayName("아카이빙 car code기반으로 BodyType을 조회한다.")
	@Test
	void findByCarCode() {
		String carCode = "LXJJ7MCT5";

		BodyTypeDto dto = repository.findByCarCode(carCode);

		assertEquals(1, dto.getId());
		assertEquals("7인승", dto.getName());
		assertEquals(0, dto.getAdditionalPrice());
	}
}
