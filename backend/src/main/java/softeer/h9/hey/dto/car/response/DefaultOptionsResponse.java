package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;
import softeer.h9.hey.dto.car.DefaultOptionDto;

@Getter
public class DefaultOptionsResponse {

	private final List<DefaultOptionDto> defaultOptions;

	private DefaultOptionsResponse(List<DefaultOptionDto> defaultOptionDtoList) {
		this.defaultOptions = defaultOptionDtoList;
	}

	public static DefaultOptionsResponse of(List<DefaultOptionDto> defaultOptionDtoList) {
		return new DefaultOptionsResponse(defaultOptionDtoList);
	}

}
