package softeer.h9.hey.service.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.DefaultOption;
import softeer.h9.hey.dto.car.DefaultOptionDto;
import softeer.h9.hey.dto.car.DefaultSubOptionDto;
import softeer.h9.hey.dto.car.request.DefaultOptionRequest;
import softeer.h9.hey.dto.car.response.DefaultOptionsResponse;
import softeer.h9.hey.repository.car.DefaultOptionRepository;

@Service
@RequiredArgsConstructor
public class DefaultOptionService {

	private final DefaultOptionRepository defaultOptionRepository;

	public DefaultOptionsResponse findAllByCarCode(DefaultOptionRequest defaultOptionRequest) {
		String carCode = defaultOptionRequest.getCarCode();

		List<DefaultOption> defaultOptions = defaultOptionRepository.findAllDefaultSubOptionByCarCode(carCode);

		Map<String, List<DefaultSubOptionDto>> defaultOptionDtoList = mapToDefaultSubOptionDtosByCategory(defaultOptions);

		return DefaultOptionsResponse.of(mapToOptionDtos(defaultOptionDtoList));
	}

	private static List<DefaultOptionDto> mapToOptionDtos(
		Map<String, List<DefaultSubOptionDto>> defaultOptionDtoList) {
		List<DefaultOptionDto> defaultSubOptionDtoList = new ArrayList<>();
		for (String key : defaultOptionDtoList.keySet()) {
			defaultSubOptionDtoList.add(DefaultOptionDto.of(key, defaultOptionDtoList.get(key)));
		}
		return defaultSubOptionDtoList;
	}

	private Map<String, List<DefaultSubOptionDto>> mapToDefaultSubOptionDtosByCategory(List<DefaultOption> defaultOptions) {
		Map<String, List<DefaultSubOptionDto>> defaultOptionDtoList = new HashMap<>();
		for (DefaultOption defaultOption : defaultOptions) {
			if (!defaultOptionDtoList.containsKey(defaultOption.getCategory())) {
				defaultOptionDtoList.put(defaultOption.getCategory(), new ArrayList<>());
			}
			defaultOptionDtoList.get(defaultOption.getCategory())
				.add(DefaultSubOptionDto.of(defaultOption.getName(), defaultOption.getImageUrl(),
					defaultOption.getDescription()));
		}
		return defaultOptionDtoList;
	}

}
