package softeer.h9.hey.dto.myChiving.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;

@Getter
@Setter
public class MyChivingRequest {
	private final int offset;
	private final int limit;

	private MyChivingRequest(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}
}
