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
public class BodyTypeDto {
	private Integer id;
	private String name;
	private Integer additionalPrice;

	public static BodyTypeDto of(Integer id, String name, Integer additionalPrice) {
		return new BodyTypeDto(id, name, additionalPrice);
	}
}
