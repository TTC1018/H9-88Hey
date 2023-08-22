package softeer.h9.hey.dto.myChiving.response;

import java.util.List;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;

@Getter
public class MyChivingsResponse {
	private Integer nextOffset;
	private List<MyChivingResponse> myChivings;

	private MyChivingsResponse(List<MyChivingResponse> myChivings) {
		this.myChivings = myChivings;
	}

	public static MyChivingsResponse from(List<MyChivingResponse> myChivings) {
		return new MyChivingsResponse(myChivings);
	}
}
