package softeer.h9.hey.service.archiving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.Archiving;
import softeer.h9.hey.domain.archiving.SelectedOption;
import softeer.h9.hey.dto.archiving.ArchivingDto;
import softeer.h9.hey.dto.archiving.ArchivingSelectedOptionDto;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;
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

@Service
@RequiredArgsConstructor
public class ArchivingService {

	private final ArchivingRepository archivingRepository;

	private final ArchivingTrimRepository trimRepository;
	private final ArchivingEngineRepository engineRepository;
	private final ArchivingBodyTypeRepository bodyTypeRepository;
	private final ArchivingWheelDriveRepository wheelDriveRepository;

	private final ArchivingInteriorColorRepository interiorColorRepository;
	private final ArchivingExteriorColorRepository exteriorColorRepository;

	private final ArchivingTagsRepository archivingTagsRepository;
	private final ArchivingSelectedOptionRepository archivingSelectedOptionRepository;

	public ArchivingResponse getArchivingFeeds(final ArchivingRequest request) {
		ArchivingResponse response = new ArchivingResponse();
		response.setNextOffset(request.getOffset() + 1);

		List<Archiving> archivings = archivingRepository.findArchivingIdByCondition(request.getModelId(),
			request.getLimit(), request.getOffset(),
			request.getSelectOptions());

		response.setArchivings(mapToArchivingDto(archivings));
		return response;
	}

	private List<ArchivingDto> mapToArchivingDto(final List<Archiving> archivings) {
		List<ArchivingDto> archivingDtos = new ArrayList<>();

		for (Archiving archiving : archivings) {
			Long archivingId = archiving.getId();
			String carCode = archiving.getCarNormalTypesId();

			ArchivingDto archivingDto = new ArchivingDto();
			archivingDto.setFeedId(archivingId);
			archivingDto.setModelName(archiving.getModelName());
			archivingDto.setCreationDate(archiving.getCreatedAt().toString());
			archivingDto.setReview(archiving.getReview());
			archivingDto.setPurchase(archiving.getIsPurchase());
			archivingDto.setReview(archiving.getReview());

			archivingDto.setTags(archivingTagsRepository.findByArchivingId(archivingId));

			archivingDto.setTrim(trimRepository.findByCarCode(carCode));
			archivingDto.setEngine(engineRepository.findByCarCode(carCode));
			archivingDto.setBodyType(bodyTypeRepository.findByCarCode(carCode));
			archivingDto.setWheelDrive(wheelDriveRepository.findByCarCode(carCode));

			archivingDto.setInteriorColor(interiorColorRepository.findByArchivingId(archivingId));
			archivingDto.setExteriorColor(exteriorColorRepository.findByArchivingId(archivingId));

			HashSet<ArchivingSelectedOptionDto> selectedOptions = new HashSet<>(
				getArchivingSelectedOptionDtoByArchivingId(archivingId));
			archivingDto.setSelectedOptions(selectedOptions);

			archivingDto.setTotalPrice(calculatePrice(archivingDto, selectedOptions));

			archivingDtos.add(archivingDto);
		}
		return archivingDtos;
	}

	private int calculatePrice(final ArchivingDto archivingDto,
		final HashSet<ArchivingSelectedOptionDto> selectedOptions) {
		// 아카이빙 총 금액을 계산하는 로직
		int totalPrice = 0;

		totalPrice += (archivingDto.getTrim().getPrice()
			+ archivingDto.getEngine().getAdditionalPrice()
			+ archivingDto.getBodyType().getAdditionalPrice()
			+ archivingDto.getWheelDrive().getAdditionalPrice()
			+ archivingDto.getExteriorColor().getAdditionalPrice());

		totalPrice += selectedOptions.stream().mapToInt(ArchivingSelectedOptionDto::getAdditionalPrice).sum();

		return totalPrice;
	}

	private List<ArchivingSelectedOptionDto> getArchivingSelectedOptionDtoByArchivingId(final Long archivingId) {
		List<SelectedOption> selectedOptions = archivingSelectedOptionRepository.findByArchiving(archivingId);
		Map<String, ArchivingSelectedOptionDto> map = new HashMap<>();

		for (SelectedOption selectedOption : selectedOptions) {
			String id = selectedOption.getId();
			// 선택한 태그가 N개 이므로, 처음 생성시에만 세팅
			if (!map.containsKey(id)) {
				map.put(id, initializeArchivingSelectedOption(selectedOption, id));
			}
			ArchivingSelectedOptionDto selectedOptionDto = map.get(id);

			selectedOptionDto.getSubOptions().add(selectedOption.getSubOption());
			selectedOptionDto.getTags().add(selectedOption.getTag());
		}
		return new ArrayList<>(map.values());
	}

	private ArchivingSelectedOptionDto initializeArchivingSelectedOption(SelectedOption selectedOption, String id) {
		ArchivingSelectedOptionDto selectedOptionDto = new ArchivingSelectedOptionDto();
		selectedOptionDto.setId(id);
		selectedOptionDto.setName(selectedOption.getName());
		selectedOptionDto.setImageUrl(selectedOption.getImageUrl());
		selectedOptionDto.setReview(selectedOption.getReview());
		selectedOptionDto.setAdditionalPrice(selectedOption.getAdditionalPrice());
		return selectedOptionDto;
	}
}
