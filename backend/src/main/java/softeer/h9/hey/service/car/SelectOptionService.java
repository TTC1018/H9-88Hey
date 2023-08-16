package softeer.h9.hey.service.car;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.dto.car.DisabledOptionIdDto;
import softeer.h9.hey.dto.car.SelectOptionByModelDto;
import softeer.h9.hey.dto.car.request.SelectOptionNormalRequest;
import softeer.h9.hey.dto.car.request.SelectOptionRequest;
import softeer.h9.hey.dto.car.response.HGenuineAccessoriesResponse;
import softeer.h9.hey.dto.car.response.HGenuineAccessoryResponse;
import softeer.h9.hey.dto.car.response.SelectOptionByModelIdResponse;
import softeer.h9.hey.dto.car.response.SelectOptionResponse;
import softeer.h9.hey.dto.car.response.SelectOptionsResponse;
import softeer.h9.hey.repository.car.SelectOptionRepository;

@Service
@RequiredArgsConstructor
public class SelectOptionService {
	private final SelectOptionRepository selectOptionRepository;

	public SelectOptionByModelIdResponse findAllSelectOptionByModelId(final SelectOptionNormalRequest request) {
		int modelId = request.getModelId();

		List<SelectOptionByModelDto> selectOptions = selectOptionRepository.findAllSelectOptionByModelId(
			modelId);

		return SelectOptionByModelIdResponse.from(selectOptions);
	}

	public SelectOptionsResponse findSelectOption(SelectOptionRequest selectOptionRequest) {
		String carCode = selectOptionRequest.getCarCode();

		List<SelectOption> selectOptions = selectOptionRepository.findSelectOptionsByCarCode(carCode);

		return mapToResponse(selectOptions);
	}

	public SelectOptionsResponse findNPerformanceOption(SelectOptionRequest selectOptionRequest) {
		String carCode = selectOptionRequest.getCarCode();

		List<SelectOption> selectOptions = selectOptionRepository.findNPerformanceByCarCode(carCode);

		return mapToResponse(selectOptions);
	}

	public HGenuineAccessoriesResponse findHGenuineOption(final SelectOptionRequest selectOptionRequest) {
		String carCode = selectOptionRequest.getCarCode();
		List<String> selectedOptionCodes = selectOptionRequest.getSelectOptions();

		List<DisabledOptionIdDto> disabledOptionIdDtos = selectOptionRepository.findDisabledOptionIdsBySelectOptionIds(
			selectedOptionCodes);
		List<SelectOption> selectOptions = selectOptionRepository.findHGenuineAccessoriesByCarCode(carCode);

		return mapToHGenuineResponse(disabledOptionIdDtos, selectOptions);
	}

	private SelectOptionsResponse mapToResponse(List<SelectOption> selectOptions) {
		List<SelectOptionResponse> selectOptionResponses = selectOptions.stream()
			.map(SelectOptionResponse::from)
			.collect(Collectors.toList());

		return SelectOptionsResponse.from(selectOptionResponses);
	}

	private HGenuineAccessoriesResponse mapToHGenuineResponse(List<DisabledOptionIdDto> disabledOptionIdDtos,
		List<SelectOption> selectOptions) {
		List<HGenuineAccessoryResponse> hGenuineAccessoryResponses = selectOptions.stream()
			.map(selectOption -> HGenuineAccessoryResponse.of(
				isAvailableOption(selectOption, disabledOptionIdDtos),
				SelectOptionResponse.from(selectOption)))
			.collect(Collectors.toList());
		System.out.println(hGenuineAccessoryResponses.size());
		return HGenuineAccessoriesResponse.of(hGenuineAccessoryResponses);
	}

	private boolean isAvailableOption(SelectOption selectOption, List<DisabledOptionIdDto> disabledOptionIdDtos) {
		if (disabledOptionIdDtos == null) {
			return true;
		}

		String selectOptionId = selectOption.getId();

		return !disabledOptionIdDtos.stream()
			.anyMatch(disabledOptionIdDto -> disabledOptionIdDto.getDisabledOptionId().equals(selectOptionId));
	}
}
