package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExteriorColorDto {
	private final Integer id;
	private final String name;
	private final String carImageUrl;
	private final String colorImageUrl;
	private final Integer additionalPrice;

	public static ExteriorColorDto of(final Integer id, final String name, final String carImageUrl,
		final String colorImageUrl,
		final Integer additionalPrice) {
		return new ExteriorColorDto(id, name, carImageUrl, colorImageUrl, additionalPrice);
	}
}
