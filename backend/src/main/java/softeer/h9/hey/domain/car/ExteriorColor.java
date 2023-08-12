package softeer.h9.hey.domain.car;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExteriorColor {
	private int id;
	private String name;
	private String carImagePath;
	private String colorImageUrl;
	private int additionalPrice;
	private List<Integer> availableInteriorColors;
	private List<Tag> tags;
}
