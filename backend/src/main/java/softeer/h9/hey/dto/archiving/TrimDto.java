package softeer.h9.hey.dto.archiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TrimDto {
	private Integer id;
	private String name;
	private Integer price;

	public static TrimDto of(final Integer id, final String name, final Integer price) {
		return new TrimDto(id, name, price);
	}
}
