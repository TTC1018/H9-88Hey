package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.dto.archiving.ExteriorColorDto;

@SpringBootTest
@DisplayName("ArchivingExteriorColor Repository 테스트")
class ArchivingExteriorColorRepositoryTest {

	@Autowired
	private ArchivingExteriorColorRepository repository;

	@DisplayName("아카이빙 id를 통해 선택한 색상 정보를 조회한다.")
	@Test
	void findByArchivingId() {
		Long id = 479893076433545675L;

		ExteriorColorDto color = repository.findByArchivingId(id);

		assertAll(
			() -> assertEquals(5, color.getId()),
			() -> assertEquals("그라파이트 그레이 메탈릭", color.getName()),
			() -> assertEquals(
				"https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/exterior/colorchip/graphite-gray-Metallic.png",
				color.getColorImageUrl())
		);
	}
}
