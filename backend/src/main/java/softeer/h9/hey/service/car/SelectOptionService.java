package softeer.h9.hey.service.car;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.repository.car.SelectOptionRepository;

@Service
@RequiredArgsConstructor
public class SelectOptionService {
	private final SelectOptionRepository selectOptionRepository;

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

	private SelectOptionsResponse mapToResponse(List<SelectOption> selectOptions) {
		List<SelectOptionResponse> selectOptionResponses = selectOptions.stream()
			.map(SelectOptionResponse::from)
			.collect(Collectors.toList());

		return SelectOptionsResponse.from(selectOptionResponses);
	}
}
