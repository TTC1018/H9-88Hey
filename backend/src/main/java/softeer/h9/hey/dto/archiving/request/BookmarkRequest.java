package softeer.h9.hey.dto.archiving.request;

import lombok.Getter;

@Getter
public class BookmarkRequest {
	private long feedId;

	public BookmarkRequest(long feed_id) {
		this.feedId = feed_id;
	}
}
