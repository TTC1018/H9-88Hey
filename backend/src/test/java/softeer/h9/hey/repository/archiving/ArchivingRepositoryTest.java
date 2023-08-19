package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.archiving.Archiving;

@SpringBootTest
@DisplayName("Archiving Repository 테스트")
class ArchivingRepositoryTest {
	@Autowired
	private ArchivingRepository repository;

	@DisplayName("페이지네이션, N개의 선택옵션, model에 따른 아카이빙을 조회한다.")
	@Test
	void findArchiving() {
		int modelId = 1;
		int limit = 8;
		int offset = 1;
		List<String> selectOptions = new ArrayList<>(); //List.of("2VS", "BIC");
		List<Archiving> archivings = repository.findArchivingIdByCondition(modelId, limit, offset, selectOptions);

		assertTrue(limit >= archivings.size());
		// 오름차순 정렬 테스트
		for (int i = 1; i < archivings.size(); i++) {
			assertTrue(archivings.get(i - 1).getId() > archivings.get(i).getId());
		}
	}
}
