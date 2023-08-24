package softeer.h9.hey.dto.myChiving;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MyChivingSelectOptionDto {
	private String id;
	private String category;
	private String name;
	private String imageUrl;
	private Integer additionalPrice;
	private List<String> subOptions;
}
