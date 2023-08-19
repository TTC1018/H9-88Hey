package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("ArchivingTags Repository 테스트")
class ArchivingTagsRepositoryTest {

	@Autowired
	private ArchivingTagsRepository repository;

	@DisplayName("선택 옵션 id에 따른 선택 태그들을 조회한다.")
	@Test
	void findBySelectOptionId() {
		Integer id = 32;

		List<String> tags = repository.findBySelectOptionId(id);

		assertAll(
			() -> assertTrue(tags.contains("깨끗하게 유지할 수 있어요\uD83E\uDDF9")),
			() -> assertTrue(tags.contains("마음에 들어요\uD83D\uDE04")),
			() -> assertTrue(tags.contains("뒷좌석도 편안해요"))
		);
	}

	@DisplayName("아카이빙 id에 따른 선택 태그들을 조회한다.")
	@Test
	void findByArchivingId() {
		Long id = 479893076433545675L;

		List<String> tags = repository.findByArchivingId(id);

		assertAll(
			() -> assertTrue(tags.contains("어린이\uD83D\uDC76")),
			() -> assertTrue(tags.contains("관리하기 편해요\uD83E\uDDF9")),
			() -> assertTrue(tags.contains("방전걱정없어요\uD83D\uDD0B"))
		);
	}
}

