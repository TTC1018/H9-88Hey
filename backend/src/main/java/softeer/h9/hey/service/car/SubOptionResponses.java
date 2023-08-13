package softeer.h9.hey.service.car;

import lombok.Builder;
import lombok.Getter;
import softeer.h9.hey.domain.car.SubOption;

@Getter
@Builder
public class SubOptionResponses {
	private String id;
	private String name;
	private String imageURL;
	private String description;

	public static SubOptionResponses from(SubOption subOption) {
		return SubOptionResponses.builder()
			.id(subOption.getId())
			.name(subOption.getName())
			.imageURL(subOption.getImageUrl())
			.description(subOption.getDescription())
			.build();
	}
}
