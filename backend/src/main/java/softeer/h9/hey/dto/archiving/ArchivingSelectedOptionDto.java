package softeer.h9.hey.dto.archiving;

import java.util.HashSet;
import java.util.Set;

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
	private Set<String> subOptions = new HashSet<>();
	private Set<String> tags = new HashSet<>();
}
