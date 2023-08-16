package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class SelectOptionByModelDto {
	private final int id;
	private final String name;

	private SelectOptionByModelDto(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public SelectOptionByModelDto of(int id, String name) {
		return new SelectOptionByModelDto(id, name);
	}
}
