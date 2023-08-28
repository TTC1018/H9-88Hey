package softeer.h9.hey.domain.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InteriorColor {
	private int id;
	private String name;
	private String carImageUrl;
	private String colorImageUrl;
}
