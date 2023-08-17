package softeer.h9.hey.dto.archiving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SelectedOptionDto {
	private String id;
	private String name;
	private String imageUrl;
	private String review;
	private Integer additionalPrice;
	private SimpleSubOptionDto subOptions;
	private TagsDto tags;
}
