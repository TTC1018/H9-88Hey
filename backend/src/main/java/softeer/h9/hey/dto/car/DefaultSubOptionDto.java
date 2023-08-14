package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class DefaultSubOptionDto {

	private final String name;
	private final String imageUrl;
	private final String description;

	private DefaultSubOptionDto(String name, String imageUrl, String description) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public static DefaultSubOptionDto of(String name, String imageUrl, String description) {
		return new DefaultSubOptionDto(name, imageUrl, description);
	}
}
