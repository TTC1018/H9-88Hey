package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class MyChivingIdResponse {
	private final Long myChivingId;

	private MyChivingIdResponse(final Long myChivingId) {
		this.myChivingId = myChivingId;
	}

	public static MyChivingIdResponse from(final Long myChivingId) {
		return new MyChivingIdResponse(myChivingId);
	}
}
