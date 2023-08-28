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
public class WheelDriveDto {
	private Integer id;
	private String name;
	private Integer additionalPrice;

	public static WheelDriveDto of(final Integer id, final String name, final Integer additionalPrice) {
		if(id == null) {
			return null;
		}

		return new WheelDriveDto(id, name, additionalPrice);
	}
}
