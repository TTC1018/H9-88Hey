package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WheelDriveDto {
	private final Integer id;
	private final String name;
	private final String additionalPrice;

	public static WheelDriveDto of(final Integer id, final String name, final String additionalPrice) {
		return new WheelDriveDto(id, name, additionalPrice);
	}
}
