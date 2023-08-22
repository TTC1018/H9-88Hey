package softeer.h9.hey.dto.myChiving;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDto {
	private Long feedId;
	private Integer userId;
	private Boolean isMarked;
}
