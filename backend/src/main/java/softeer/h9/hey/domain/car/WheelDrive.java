package softeer.h9.hey.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WheelDrive {
	private int id;
	private String name;
	private String description;
	private int additionalPrice;
	private String imageUrl;
	private int modelId;
}
