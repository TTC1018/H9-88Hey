package softeer.h9.hey.service.myChiving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.myChiving.MyChivingDto;
import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionFetchDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
import softeer.h9.hey.dto.myChiving.response.MyChivingResponse;
import softeer.h9.hey.dto.myChiving.response.MyChivingsResponse;
import softeer.h9.hey.repository.myChiving.MyChivingRepository;

@Service
@RequiredArgsConstructor
public class MyChivingService {

	@Getter
	private enum IsSubmit {

		SUBMIT(true),
		TEMPORARY_SUBMIT(false);

		private final boolean value;

		IsSubmit(boolean value) {
			this.value = value;
		}
	}

	private final MyChivingRepository myChivingRepository;

	//최종저장
	public MyChivingIdResponse saveMyCar(int userId, final MyChivingSaveRequest myChivingSaveRequest) {

		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.SUBMIT.value);

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.from(myChivingId);
	}

	//임시 저장
	public MyChivingIdResponse temporarySaveMyCar(int userId, final MyChivingTempSaveRequest myChivingSaveRequest) {
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.TEMPORARY_SUBMIT.value);

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.from(myChivingId);
	}

	public MyChivingsResponse findMyChivings(final int userId, final MyChivingRequest myChivingRequest) {
		int limit = myChivingRequest.getLimit();
		int startIndex = (myChivingRequest.getOffset() - 1) * limit;

		List<MyChivingDto> myChivingDtoList = myChivingRepository.findMyChivingsByUserIdLimitAndOffset(userId, limit, startIndex);

		return MyChivingsResponse.from(findMyChivingDetailById(myChivingDtoList));
	}

	private List<MyChivingResponse> findMyChivingDetailById(List<MyChivingDto> myChivingDtoList) {
		if (myChivingDtoList.isEmpty()) {
			return new ArrayList<>();
		}

		List<Long> myChivingIdList = myChivingDtoList.stream()
			.map(MyChivingDto::getMyChivingId)
			.collect(Collectors.toList());

		List<MyChivingSelectOptionFetchDto> myChivingSelectOptionFetchDtoList = myChivingRepository.findOptionDataByMyChivingIdList(myChivingIdList);

		Map<String, Set<String>> subOptionMap = new HashMap<>();
		Map<Long, Set<String>> selectOptionMap = new HashMap<>();
		Map<String, MyChivingSelectOptionDto> selectOptionContentMap = new HashMap<>();

		for (MyChivingSelectOptionFetchDto myChivingSelectOptionFetchDto : myChivingSelectOptionFetchDtoList) {
			//선택옵션에 해당하는 하위옵션을 넣기 위한 코드, 처음에 집합 초기화
			initSubOptionData(subOptionMap, myChivingSelectOptionFetchDto.getSelectOptionId(),
				myChivingSelectOptionFetchDto.getSubOptionName());
			//마이카이빙에서 가지고있는 선택 옵션 매핑
			initSelectOptionData(selectOptionMap, myChivingSelectOptionFetchDto.getMyChivingId(),
				myChivingSelectOptionFetchDto.getSelectOptionId());
		}

		//선택 옵션이 가지고 있는 정보 매핑
		for (MyChivingSelectOptionFetchDto myChivingSelectOptionFetchDto : myChivingSelectOptionFetchDtoList) {
			initSelectOptionContentMap(selectOptionContentMap, subOptionMap, myChivingSelectOptionFetchDto);
		}

		return mapToMyChivingResponse(myChivingDtoList, selectOptionMap, selectOptionContentMap);
	}

	private void initSubOptionData(Map<String, Set<String>> subOptionMap, String selectOptionId, String subOptionName) {
		if (!subOptionMap.containsKey(selectOptionId)) {
			subOptionMap.put(selectOptionId, new HashSet<>());
		}

		subOptionMap.get(selectOptionId).add(subOptionName);
	}

	private void initSelectOptionData(Map<Long, Set<String>> selectOptionMap, Long myChivingId, String selectOptionId) {
		if (!selectOptionMap.containsKey(myChivingId)) {
			selectOptionMap.put(myChivingId, new HashSet<>());
		}

		selectOptionMap.get(myChivingId).add(selectOptionId);
	}

	private void initSelectOptionContentMap(Map<String, MyChivingSelectOptionDto> selectOptionContentMap,
		Map<String, Set<String>> subOptionMap,
		MyChivingSelectOptionFetchDto myChivingSelectOptionFetchDto) {
		if (!selectOptionContentMap.containsKey(myChivingSelectOptionFetchDto.getSelectOptionId())) {
			MyChivingSelectOptionDto myChivingSelectOptionDto =
				MyChivingSelectOptionDto.builder()
					.id(myChivingSelectOptionFetchDto.getSelectOptionId())
					.name(myChivingSelectOptionFetchDto.getSelectOptionName())
					.imageUrl(myChivingSelectOptionFetchDto.getImageUrl())
					.additionalPrice(myChivingSelectOptionFetchDto.getAdditionalPrice())
					.subOptions(subOptionMap.get(myChivingSelectOptionFetchDto.getSelectOptionId())
						.stream().collect(Collectors.toList()))
					.build();

			selectOptionContentMap.put(myChivingSelectOptionFetchDto.getSelectOptionId(),
				myChivingSelectOptionDto);
		}
	}

	private List<MyChivingResponse> mapToMyChivingResponse(List<MyChivingDto> myChivingDtoList,
		Map<Long, Set<String>> selectOptionMap, Map<String, MyChivingSelectOptionDto> selectOptionContentMap) {

		List<MyChivingResponse> myChivingResponseList = new ArrayList<>();
		myChivingDtoList.stream()
			.forEach(myChivingDto -> {
				List<MyChivingSelectOptionDto> selectOptionDtoList = new ArrayList<>();

				if (selectOptionMap.containsKey(myChivingDto.getMyChivingId())) {
					selectOptionMap.get(myChivingDto.getMyChivingId()).stream()
						.forEach(selectOptionId -> {
							selectOptionDtoList.add(selectOptionContentMap.get(selectOptionId));
						});
				}

				myChivingResponseList.add(MyChivingResponse.of(myChivingDto, selectOptionDtoList));
			});

		return myChivingResponseList;
	}
}
