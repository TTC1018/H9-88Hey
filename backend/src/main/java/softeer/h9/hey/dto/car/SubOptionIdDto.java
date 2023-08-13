package softeer.h9.hey.dto.car;

public class SubOptionIdDto {
	private final String subOptionId;

	private SubOptionIdDto(final String subOptionId) {
		this.subOptionId = subOptionId;
	}

	public static SubOptionIdDto of(final String subOptionId) {
		return new SubOptionIdDto(subOptionId);
	}
}
