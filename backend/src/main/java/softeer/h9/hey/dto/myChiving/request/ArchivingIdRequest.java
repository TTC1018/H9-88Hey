package softeer.h9.hey.dto.myChiving.request;

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
}
