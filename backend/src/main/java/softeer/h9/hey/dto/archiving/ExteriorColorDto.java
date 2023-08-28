package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ExteriorColorDto {
	private Integer id;
	private String name;
	private String carImageUrl;
	private String colorImageUrl;
	private Integer additionalPrice;

	public static ExteriorColorDto of(final Integer id, final String name, final String carImageUrl,
		final String colorImageUrl,
		final Integer additionalPrice) {
		if(id == null) {
			return null;
		}

		return new ExteriorColorDto(id, name, carImageUrl, colorImageUrl, additionalPrice);
	}
}
