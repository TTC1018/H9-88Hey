package softeer.h9.hey.dto.car;

import lombok.Getter;

@Getter
public class DisabledOptionIdDto {
	private final String disabledOptionId;

	private DisabledOptionIdDto(final String disabledOptionId) {
		this.disabledOptionId = disabledOptionId;
	}

	public static DisabledOptionIdDto of(final String disabledOptionId) {
		return new DisabledOptionIdDto(disabledOptionId);
	}
}
