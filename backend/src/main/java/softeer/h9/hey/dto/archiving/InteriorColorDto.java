package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class InteriorColorDto {
	private Integer id;
	private String name;
	private String colorImageUrl;

	public static InteriorColorDto of(final Integer id, final String name, final String colorImageUrl) {
		return new InteriorColorDto(id, name, colorImageUrl);
	}
}
