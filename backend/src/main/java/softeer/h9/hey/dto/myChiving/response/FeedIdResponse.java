package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class FeedIdResponse {
	private String feedId;

	private FeedIdResponse(String feedId) {
		this.feedId = feedId;
	}

	public static FeedIdResponse of(Long feedId) {
		return new FeedIdResponse(Long.toString(feedId));
	}
}
