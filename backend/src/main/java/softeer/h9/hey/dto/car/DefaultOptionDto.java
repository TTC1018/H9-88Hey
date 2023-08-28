package softeer.h9.hey.dto.car;

import java.util.List;

import lombok.Getter;

@Getter
public class DefaultOptionDto {

	private final String category;
	private final List<DefaultSubOptionDto> subOptions;

	private DefaultOptionDto(String defaultOptionCategory, List<DefaultSubOptionDto> defaultSubOptionDtoList) {
		this.category = defaultOptionCategory;
		this.subOptions = defaultSubOptionDtoList;
	}

	public static DefaultOptionDto of(String defaultOptionCategory, List<DefaultSubOptionDto> defaultSubOptionDtoList) {
		return new DefaultOptionDto(defaultOptionCategory, defaultSubOptionDtoList);
	}
}
