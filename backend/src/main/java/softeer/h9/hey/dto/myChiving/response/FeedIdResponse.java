package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class FeedIdResponse {
	private Long feedId;

	private FeedIdResponse(Long feedId) {
		this.feedId = feedId;
	}

	public static FeedIdResponse of(Long feedId) {
		return new FeedIdResponse(feedId);
	}
}
