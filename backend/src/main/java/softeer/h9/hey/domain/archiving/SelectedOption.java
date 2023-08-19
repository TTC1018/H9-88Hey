package softeer.h9.hey.domain.archiving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SelectedOption {
	private String id;
	private String name;
	private String imageUrl;
	private String review;
	private Integer additionalPrice;
	private String subOption;
	private String tag;
}
