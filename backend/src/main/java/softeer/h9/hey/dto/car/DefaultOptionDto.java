package softeer.h9.hey.dto.car;

import java.util.List;

import lombok.Getter;

@Getter
public class DefaultOptionDto {

	private final String defaultOptionCategory;
	private final List<DefaultSubOptionDto> subOptions;

	private DefaultOptionDto(String defaultOptionCategory, List<DefaultSubOptionDto> defaultSubOptionDtoList) {
		this.defaultOptionCategory = defaultOptionCategory;
		this.subOptions = defaultSubOptionDtoList;
	}

	public static DefaultOptionDto of(String defaultOptionCategory, List<DefaultSubOptionDto> defaultSubOptionDtoList) {
		return new DefaultOptionDto(defaultOptionCategory, defaultSubOptionDtoList);
	}
}
