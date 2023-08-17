package softeer.h9.hey.repository.archiving;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.archiving.Archiving;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;

@SpringBootTest
@DisplayName("Archiving Repository 테스트")
class ArchivingRepositoryTest {
	@Autowired
	private ArchivingRepository repository;

	@DisplayName("페이지네이션, N개의 선택옵션, model에 따른 아카이빙을 조회한다.")
	@Test
	void findArchiving() {
		int modelId = 1;
		int offset = 1;
		int limit = 8;
		List<String> selectOptions = List.of("2VS", "BIC");
		ArchivingRequest request = ArchivingRequest.of(modelId, offset, limit, selectOptions);

		List<Archiving> archivings = repository.findArchivingIdByCondition(request);


		assertTrue(limit <= archivings.size());
		// 오름차순 정렬 테스트
		for (int i = 1; i < archivings.size(); i++) {
			assertTrue(archivings.get(i - 1).getId() > archivings.get(i).getId());
		}
	}
}
