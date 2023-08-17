package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class InteriorColorDto {
	private final Integer id;
	private final String name;
	private final String colorImageUrl;

	public static InteriorColorDto of(final Integer id, final String name, final String colorImageUrl) {
		return new InteriorColorDto(id, name, colorImageUrl);
	}
}
