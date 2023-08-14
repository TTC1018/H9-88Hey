package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class DefaultSubOptionDto {

	private final int id;
	private final String name;
	private final String imageUrl;
	private final String description;

	private DefaultSubOptionDto(int id, String name, String imageUrl, String description) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public static DefaultSubOptionDto of(int id, String name, String imageUrl, String description) {
		return new DefaultSubOptionDto(id, name, imageUrl, description);
	}
}
