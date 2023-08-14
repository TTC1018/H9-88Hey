package softeer.h9.hey.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultOption {
	private String id;
	private String name;
	private String description;
	private String imageUrl;
	private String category;
}
