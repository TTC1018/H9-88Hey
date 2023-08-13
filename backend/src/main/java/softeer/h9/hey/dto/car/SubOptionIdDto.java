package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class SubOptionIdDto {
	private final String subOptionId;

	private SubOptionIdDto(final String subOptionId) {
		this.subOptionId = subOptionId;
	}

	public static SubOptionIdDto of(final String subOptionId) {
		return new SubOptionIdDto(subOptionId);
	}
}
