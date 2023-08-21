package softeer.h9.hey.dto.myChiving.response;

import java.util.List;

import lombok.Getter;

@Getter
public class MyChivingsResponse {
	private List<MyChivingResponse> myChivings;

	private MyChivingsResponse(List<MyChivingResponse> myChivings) {
		this.myChivings = myChivings;
	}

	public static MyChivingsResponse from(List<MyChivingResponse> myChivings) {
		return new MyChivingsResponse(myChivings);
	}
}
