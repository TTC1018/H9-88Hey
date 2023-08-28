package softeer.h9.hey.dto.archiving;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SelectOptionDto {
	private String id;
	private String name;
	private String imageUrl;
	private String review;
	private String category;
	private Integer additionalPrice;
	private List<String> subOptions;
	private List<String> tags;
}
