package softeer.h9.hey.service.archiving;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.archiving.ArchivingResult;
import softeer.h9.hey.dto.archiving.request.ArchivingDetailRequest;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;
import softeer.h9.hey.dto.archiving.response.ArchivingDetailResponse;
import softeer.h9.hey.dto.archiving.response.ArchivingResponse;
import softeer.h9.hey.repository.archiving.ArchivingRepository;
import softeer.h9.hey.repository.archiving.ArchivingTagsRepository;

@DisplayName("Archiving Service 테스트")
class ArchivingServiceTest {

	ArchivingRepository archivingRepository = Mockito.mock(ArchivingRepository.class);
	ArchivingTagsRepository archivingTagsRepository = Mockito.mock(ArchivingTagsRepository.class);
	ArchivingTagsRepository tagsRepository = Mockito.mock(ArchivingTagsRepository.class);

	ArchivingService archivingService = new ArchivingService(
		archivingRepository,
		archivingTagsRepository,
		tagsRepository);

	@DisplayName("단일 아카이빙 상세 정보를 조회한다.")
	@Test
	void getArchivingDetail() {
		Long id = 123456L;
		ArchivingDetailRequest request = ArchivingDetailRequest.of(id);

		ArchivingResult archivingResult1 = ArchivingResult.builder()
			.feedId(id)
			.selectOptionId("option1")
			.subOptionName("subOption1")
			.trimPrice(10)
			.enginePrice(10)
			.bodyTypePrice(10)
			.wheelDrivePrice(10)
			.exteriorColorAdditionalPrice(10)
			.selectOptionAdditionalPrice(10)
			.creationDate(LocalDate.now())
			.build();

		ArchivingResult archivingResult2 = ArchivingResult.builder()
			.feedId(id)
			.selectOptionId("option1")
			.subOptionName("subOption2")
			.trimPrice(10)
			.enginePrice(10)
			.bodyTypePrice(10)
			.wheelDrivePrice(10)
			.exteriorColorAdditionalPrice(10)
			.selectOptionAdditionalPrice(10)
			.creationDate(LocalDate.now())
			.build();

		ArchivingResult archivingResult3 = ArchivingResult.builder()
			.feedId(id)
			.selectOptionId("option2")
			.subOptionName("subOption3")
			.trimPrice(10)
			.enginePrice(10)
			.bodyTypePrice(10)
			.wheelDrivePrice(10)
			.exteriorColorAdditionalPrice(10)
			.selectOptionAdditionalPrice(10)
			.creationDate(LocalDate.now())
			.build();

		List<ArchivingResult> archivingResults = List.of(archivingResult1, archivingResult2, archivingResult3);

		Mockito.when(archivingRepository.findDetailByFeedId(id))
			.thenReturn(archivingResults);

		ArchivingDetailResponse response = archivingService.getArchivingDetail(id);

		assertEquals(2, response.getSelectedOptions().size());
		assertEquals(70, response.getTotalPrice());
	}

	@DisplayName("아카이빙 피드들을 페이지네이션을 적용하여 조회한다.")
	@Test
	void getArchivingFeeds() {
		int modelId = 1;
		int offset = 1;
		int limit = 8;
		List<String> selectOptions = List.of("2VS, HSS");
		ArchivingRequest request = ArchivingRequest.of(modelId, offset, limit, selectOptions);

		ArchivingResponse response = archivingService.getArchivingFeeds(request);

		assertEquals(offset + 1, response.getNextOffset());
	}
}
