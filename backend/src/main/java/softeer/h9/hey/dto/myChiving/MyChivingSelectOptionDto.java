package softeer.h9.hey.dto.myChiving;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class MyChivingSelectOptionDto {
	private String id;
	private String name;
	private String imageUrl;
	private Integer additionalPrice;
}
