package softeer.h9.hey.dto.archiving;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArchivingSelectedOptionDto {
	private String id;
	private String name;
	private String imageUrl;
	private String review;
	private Integer additionalPrice;
	private List<String> subOptions = new ArrayList<>();
	private List<String> tags = new ArrayList<>();
}
