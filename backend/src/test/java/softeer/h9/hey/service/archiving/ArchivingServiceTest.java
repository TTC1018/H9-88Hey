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
import softeer.h9.hey.repository.archiving.ArchivingBodyTypeRepository;
import softeer.h9.hey.repository.archiving.ArchivingEngineRepository;
import softeer.h9.hey.repository.archiving.ArchivingExteriorColorRepository;
import softeer.h9.hey.repository.archiving.ArchivingInteriorColorRepository;
import softeer.h9.hey.repository.archiving.ArchivingRepository;
import softeer.h9.hey.repository.archiving.ArchivingSelectedOptionRepository;
import softeer.h9.hey.repository.archiving.ArchivingTagsRepository;
import softeer.h9.hey.repository.archiving.ArchivingTrimRepository;
import softeer.h9.hey.repository.archiving.ArchivingWheelDriveRepository;

@DisplayName("Archiving Service 테스트")
class ArchivingServiceTest {

	ArchivingRepository archivingRepository = Mockito.mock(ArchivingRepository.class);
	ArchivingTrimRepository trimRepository = Mockito.mock(ArchivingTrimRepository.class);
	ArchivingEngineRepository engineRepository = Mockito.mock(ArchivingEngineRepository.class);
	ArchivingBodyTypeRepository bodyTypeRepository = Mockito.mock(ArchivingBodyTypeRepository.class);
	ArchivingWheelDriveRepository wheelDriveRepository = Mockito.mock(ArchivingWheelDriveRepository.class);
	ArchivingInteriorColorRepository interiorColorRepository = Mockito.mock(ArchivingInteriorColorRepository.class);
	ArchivingExteriorColorRepository exteriorColorRepository = Mockito.mock(ArchivingExteriorColorRepository.class);
	ArchivingTagsRepository archivingTagsRepository = Mockito.mock(ArchivingTagsRepository.class);
	ArchivingSelectedOptionRepository archivingSelectedOptionRepository = Mockito.mock(
		ArchivingSelectedOptionRepository.class);
	ArchivingTagsRepository tagsRepository = Mockito.mock(ArchivingTagsRepository.class);

	ArchivingService archivingService = new ArchivingService(
		archivingRepository,
		trimRepository,
		engineRepository,
		bodyTypeRepository,
		wheelDriveRepository,
		interiorColorRepository,
		exteriorColorRepository,
		archivingTagsRepository,
		archivingSelectedOptionRepository,
		tagsRepository);

	@DisplayName("단일 아카이빙 상세 정보를 조회한다.")
	@Test
	void getArchivingDetail() {
		Long id = 123456L;
		ArchivingDetailRequest request = ArchivingDetailRequest.of(id);

		ArchivingResult archivingResult1 = ArchivingResult.builder()
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

		ArchivingDetailResponse response = archivingService.getArchivingDetail(request);

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
