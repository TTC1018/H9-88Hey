package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EngineDto {
	private final Integer id;
	private final String name;
	private final String additionalPrice;

	public static EngineDto of(final Integer id, final String name, final String additionalPrice) {
		return new EngineDto(id, name, additionalPrice);
	}
}
