package softeer.h9.hey.dto.myChiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.TrimDto;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelDto {
	private Integer id;
	private String name;

	public static ModelDto of(final Integer id, final String name) {
		if(id == null) {
			return null;
		}

		return new ModelDto(id, name);
	}
}
