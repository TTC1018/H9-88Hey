package softeer.h9.hey.dto.myChiving;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PROTECTED)
@Builder
public class MyChivingSelectOptionFetchDto {
	private Long myChivingId;
	private String selectOptionId;
	private String selectOptionName;
	private String imageUrl;
	private Integer additionalPrice;
	private String subOptionName;
}
