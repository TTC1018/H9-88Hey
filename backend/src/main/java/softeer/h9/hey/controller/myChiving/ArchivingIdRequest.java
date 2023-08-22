package softeer.h9.hey.controller.myChiving;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArchivingIdRequest {
	private Long feedId;

	@JsonCreator
	private ArchivingIdRequest(@JsonProperty("feed_id") Long feed_id) {
		this.feedId = feed_id;
	}

	public static ArchivingIdRequest from(Long feedId) {
		return new ArchivingIdRequest(feedId);
	}
}
