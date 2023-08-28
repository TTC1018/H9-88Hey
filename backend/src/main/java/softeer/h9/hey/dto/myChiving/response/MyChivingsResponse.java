package softeer.h9.hey.dto.myChiving.response;

import java.util.List;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;

@Getter
public class MyChivingsResponse {
	private Integer nextOffset;
	private List<MyChivingResponse> myChivings;

	private MyChivingsResponse(List<MyChivingResponse> myChivings, Integer nextOffset) {
		this.myChivings = myChivings;
		this.nextOffset = nextOffset;
	}

	public static MyChivingsResponse from(List<MyChivingResponse> myChivings, Integer nextOffset) {
		return new MyChivingsResponse(myChivings, nextOffset);
	}
}
