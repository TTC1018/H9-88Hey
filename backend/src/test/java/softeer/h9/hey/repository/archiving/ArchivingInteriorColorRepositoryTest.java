package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.dto.archiving.InteriorColorDto;

@SpringBootTest
@DisplayName("ArchivingInteriorColor Repository 테스트")
class ArchivingInteriorColorRepositoryTest {

	@Autowired
	private ArchivingInteriorColorRepository repository;

	@DisplayName("아카이빙 id를 통해 선택한 색상 정보를 조회한다.")
	@Test
	void findByArchivingId() {
		Long id = 479893076433545675L;

		InteriorColorDto interiorColor = repository.findByArchivingId(id);

		assertAll(
			() -> assertEquals(1, interiorColor.getId()),
			() -> assertEquals("퀄팅천연(블랙)", interiorColor.getName()),
			() -> assertEquals(
				"https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/interior/colorchip/quilting-natural-black.png",
				interiorColor.getColorImageUrl())
		);
	}
}
