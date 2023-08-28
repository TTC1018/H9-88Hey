package softeer.h9.hey.service.archiving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.Archiving;
import softeer.h9.hey.domain.archiving.ArchivingResult;
import softeer.h9.hey.domain.archiving.Feed;
import softeer.h9.hey.domain.archiving.SelectOptionTag;
import softeer.h9.hey.dto.archiving.ArchivingDto;
import softeer.h9.hey.dto.archiving.ArchivingSelectedOptionDto;
import softeer.h9.hey.dto.archiving.BodyTypeDto;
import softeer.h9.hey.dto.archiving.EngineDto;
import softeer.h9.hey.dto.archiving.ExteriorColorDto;
import softeer.h9.hey.dto.archiving.InteriorColorDto;
import softeer.h9.hey.dto.archiving.SelectOptionDto;
import softeer.h9.hey.dto.archiving.TrimDto;
import softeer.h9.hey.dto.archiving.WheelDriveDto;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;
import softeer.h9.hey.dto.archiving.request.PaginationRequest;
import softeer.h9.hey.dto.archiving.response.ArchivingDetailResponse;
import softeer.h9.hey.dto.archiving.response.ArchivingResponse;
import softeer.h9.hey.dto.archiving.response.ArchivingsByUserBookmarkedResponse;
import softeer.h9.hey.repository.archiving.ArchivingRepository;
import softeer.h9.hey.repository.archiving.ArchivingTagsRepository;

@Service
@RequiredArgsConstructor
public class ArchivingService {

	private final ArchivingRepository archivingRepository;
	private final ArchivingTagsRepository archivingTagsRepository;
	private final ArchivingTagsRepository tagsRepository;

	public ArchivingsByUserBookmarkedResponse findAllByUserId(final int userId, PaginationRequest request) {
		int limit = request.getLimit();
		int offset = request.getOffset();

		List<Feed> feedsByUserBookmarked = archivingRepository.findArchivingsByUserIdWithPagination(
			userId, limit, offset);

		List<ArchivingDetailResponse> archivingDtos = new ArrayList<>();

		for (Feed feed : feedsByUserBookmarked) {
			archivingDtos.add(getArchivingDetail(feed.getFeedId()));
		}

		return ArchivingsByUserBookmarkedResponse.of(archivingDtos, offset + 1);
	}

	public ArchivingDetailResponse getArchivingDetail(final Long feedId) {
		List<ArchivingResult> archivingResults = archivingRepository.findDetailByFeedId(feedId);
		List<SelectOptionTag> selectOptionTags = tagsRepository.findAllByArchivingIdSelectedOptions(feedId);
		List<String> tags = archivingTagsRepository.findByArchivingId(feedId);

		ArchivingDetailResponse response = new ArchivingDetailResponse();
		response.setTotalPrice(initializeDefaultPrice(archivingResults));
		response.setTags(tags);

		initializeDefault(archivingResults, response);
		initializeSelectOptions(archivingResults, selectOptionTags, response);

		return response;
	}

	private int initializeDefaultPrice(List<ArchivingResult> archivingResults) {
		int totalPrice = 0;
		if (archivingResults.isEmpty()) {
			return 0;
		}

		ArchivingResult result = archivingResults.get(0);
		totalPrice +=
			result.getTrimPrice()
				+ result.getEnginePrice()
				+ result.getBodyTypePrice()
				+ result.getWheelDrivePrice()
				+ result.getExteriorColorAdditionalPrice();

		return totalPrice;
	}

	private void initializeSelectOptions(List<ArchivingResult> archivingResults, List<SelectOptionTag> selectOptionTags,
		ArchivingDetailResponse response) {
		Map<String, SelectOptionDto> selectOptions = new HashMap<>();

		for (ArchivingResult archivingResult : archivingResults) {
			String selectOptionId = archivingResult.getSelectOptionId();

			if (!selectOptions.containsKey(selectOptionId)) {
				SelectOptionDto selectOptionDto = new SelectOptionDto();
				selectOptionDto.setSubOptions(new ArrayList<>());

				// 태그 넣어주기.
				selectOptionDto.setTags(initializeTags(selectOptionTags, selectOptionId));

				selectOptions.put(selectOptionId, selectOptionDto);

				response.setTotalPrice(response.getTotalPrice() + archivingResult.getSelectOptionAdditionalPrice());

			}

			SelectOptionDto selectOptionDto = selectOptions.get(selectOptionId);
			selectOptionDto.setId(archivingResult.getSelectOptionId());
			selectOptionDto.setName(archivingResult.getSelectOptionName());
			selectOptionDto.setImageUrl(archivingResult.getSelectOptionImageUrl());
			selectOptionDto.setReview(archivingResult.getSelectOptionReview());
			selectOptionDto.setAdditionalPrice(archivingResult.getSelectOptionAdditionalPrice());
			selectOptionDto.setCategory(archivingResult.getSelectOptionCategory());
			selectOptionDto.getSubOptions().add(archivingResult.getSubOptionName());
		}

		response.setSelectedOptions(new ArrayList<>(selectOptions.values()));
	}

	private List<String> initializeTags(List<SelectOptionTag> selectOptionTags, String selectOptionId) {
		List<String> tags = new ArrayList<>();
		for (SelectOptionTag selectOptionTag : selectOptionTags) {
			if (selectOptionTag.getSelectOptionId().equals(selectOptionId)) {
				tags.add(selectOptionTag.getTag());
			}
		}
		return tags;
	}

	private void initializeDefault(List<ArchivingResult> archivingResults, ArchivingDetailResponse response) {
		for (ArchivingResult archivingResult : archivingResults) {
			// 객체 매핑
			response.setTrim(
				TrimDto.of(archivingResult.getTrimId(), archivingResult.getTrimName(), archivingResult.getTrimPrice()));
			response.setEngine(
				EngineDto.of(archivingResult.getEngineId(), archivingResult.getEngineName(),
					archivingResult.getEnginePrice()));
			response.setBodyType(
				BodyTypeDto.of(archivingResult.getBodyTypeId(), archivingResult.getBodyTypeName(),
					archivingResult.getBodyTypePrice()));
			response.setWheelDrive(
				WheelDriveDto.of(archivingResult.getWheelDriveId(), archivingResult.getWheelDriveName(),
					archivingResult.getWheelDrivePrice()));
			response.setExteriorColor(
				ExteriorColorDto.of(archivingResult.getExteriorColorId(), archivingResult.getExteriorColorName(),
					archivingResult.getExteriorColorCarImageUrl(), archivingResult.getExteriorColorColorImageUrl(),
					archivingResult.getExteriorColorAdditionalPrice()));
			response.setInteriorColor(
				InteriorColorDto.of(archivingResult.getInteriorColorId(), archivingResult.getInteriorColorName(),
					archivingResult.getInteriorColorColorImageUrl()));

			// 기본 매핑
			response.setFeedId(Long.toString(archivingResult.getFeedId()));
			response.setModelName(archivingResult.getModelName());
			response.setReview(archivingResult.getReview());
			response.setPurchase(archivingResult.isPurchase());
			response.setCreationDate(archivingResult.getCreationDate().toString());
		}
	}

	public ArchivingResponse getArchivingFeeds(final ArchivingRequest request) {
		ArchivingResponse response = new ArchivingResponse();
		response.setNextOffset(request.getOffset() + 1);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		List<Archiving> resultFeedIds = archivingRepository.findArchivingIdByCondition(request.getModelId(),
			request.getLimit(), request.getOffset(),
			request.getSelectOptions());

		stopWatch.stop();
		System.out.println("stopWatch = " + stopWatch.prettyPrint());

		// 아카이빙 피드
		List<Long> feedIds = new ArrayList<>();
		for (Archiving archiving : resultFeedIds) {
			feedIds.add(archiving.getFeedId());
		}

		stopWatch.start();
		List<Archiving> results = archivingRepository.findAllByFeedIds(feedIds);
		stopWatch.stop();
		System.out.println("stopWatch = " + stopWatch.prettyPrint());

		Map<Long, ArchivingDto> archivingMap = new HashMap<>();
		for (Archiving result : results) {
			Long feedId = result.getFeedId();
			if (!archivingMap.containsKey(feedId)) {
				archivingMap.put(feedId, new ArchivingDto());
				ArchivingDto currentArchivingDto = archivingMap.get(feedId);

				// 기본 중복 데이터 세팅
				currentArchivingDto.setFeedId(Long.toString(feedId));
				currentArchivingDto.setModelName(result.getModelName());
				currentArchivingDto.setPurchase(result.getIsPurchase());
				currentArchivingDto.setReview(result.getReview());
				currentArchivingDto.setCreationDate(result.getCreatedAt().toString());
				// Dto 중복 데이터 세팅
				currentArchivingDto.setTrim(
					TrimDto.builder()
						.name(result.getTrimName())
						.build());
				currentArchivingDto.setEngine(
					EngineDto.builder()
						.name(result.getEngineName())
						.build());
				currentArchivingDto.setBodyType(
					BodyTypeDto.builder()
						.name(result.getBodyTypeName())
						.build());
				currentArchivingDto.setWheelDrive(
					WheelDriveDto.builder()
						.name(result.getWheelDriveName())
						.build());
				currentArchivingDto.setInteriorColor(
					InteriorColorDto.builder()
						.name(result.getInteriorColorName())
						.build());
				currentArchivingDto.setExteriorColor(
					ExteriorColorDto.builder()
						.name(result.getExteriorColorName())
						.build());
				currentArchivingDto.setSelectedOptions(new ArrayList<>());
			}
			ArchivingDto currentArchivingDto = archivingMap.get(feedId);

			// 선택 옵션 추가
			ArchivingSelectedOptionDto temp = ArchivingSelectedOptionDto
				.builder()
				.name(result.getSelectOptionName())
				.id(result.getSelectOptionId())
				.build();
			currentArchivingDto.getSelectedOptions().add(temp);


		}

		response.setArchivings(new ArrayList<>(archivingMap.values()));

		return response;
	}

}
