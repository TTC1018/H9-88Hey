package softeer.h9.hey.domain.archiving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Feed {
	private long feedId;
	private int userId;
	private Boolean isMarked;
}
