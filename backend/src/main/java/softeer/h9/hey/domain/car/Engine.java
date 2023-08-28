package softeer.h9.hey.domain.car;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
	private int id;
	private int additionalPrice;
	private int modelId;
	private String name;
	private String imageUrl;
	private String description;
	private String maximumPower;
	private String maximumTorque;
}
