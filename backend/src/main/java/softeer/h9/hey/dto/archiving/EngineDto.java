package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EngineDto {
	private Integer id;
	private String name;
	private Integer additionalPrice;

	public static EngineDto of(final Integer id, final String name, final Integer additionalPrice) {
		return new EngineDto(id, name, additionalPrice);
	}
}
