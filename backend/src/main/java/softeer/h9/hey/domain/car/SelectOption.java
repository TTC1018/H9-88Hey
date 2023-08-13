package softeer.h9.hey.domain.car;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SelectOption {
	private String id;
	private String name;
	private Integer additionalPrice;
	private String imageUrl;
	private Category category;
	private List<Tag> tags = new ArrayList<>();
	private List<SubOption> subOptions = new ArrayList<>();

	public void addSubOption(SubOption subOption) {
		subOptions.add(subOption);
	}
}
