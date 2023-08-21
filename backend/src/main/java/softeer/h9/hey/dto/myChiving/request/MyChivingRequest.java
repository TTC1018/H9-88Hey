package softeer.h9.hey.dto.myChiving.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.request.ArchivingRequest;

@Getter
@Setter
public class MyChivingRequest {
	private int offset;
	private int limit;

	private MyChivingRequest(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public static MyChivingRequest of(final int offset, final int limit) {
		return new MyChivingRequest(offset, limit);
	}
}
