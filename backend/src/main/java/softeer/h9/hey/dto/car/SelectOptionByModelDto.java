package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class SelectOptionByModelDto {
	private final String id;
	private final String name;
	private final String category;

	private SelectOptionByModelDto(String id, String name, String category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public static SelectOptionByModelDto of(final String id, final String name, final String category) {
		return new SelectOptionByModelDto(id, name, category);
	}
}
