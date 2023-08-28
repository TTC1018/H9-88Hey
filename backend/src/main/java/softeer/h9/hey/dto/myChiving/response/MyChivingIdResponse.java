package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class MyChivingIdResponse {
	private final String myChivingId;

	private MyChivingIdResponse(final String myChivingId) {
		this.myChivingId = myChivingId;
	}

	public static MyChivingIdResponse from(final Long myChivingId) {
		return new MyChivingIdResponse(Long.toString(myChivingId));
	}
}
