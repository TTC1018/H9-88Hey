package softeer.h9.hey.controller.myChiving;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
import softeer.h9.hey.service.myChiving.MyChivingService;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequiredArgsConstructor
public class MyChivingController {

	private final MyChivingService myChivingService;

	@PostMapping("/mychiving")
	public GlobalResponse<MyChivingIdResponse> saveMyCarToMyChiving(@LoginUser int userId, @Valid @RequestBody MyChivingSaveRequest myChivingSaveRequest) {
		MyChivingIdResponse response = myChivingService.saveMyCar(userId, myChivingSaveRequest);
		return GlobalResponse.ok(response);
	}

	@PostMapping("/mychiving/temp")
	public GlobalResponse<MyChivingIdResponse> temporarySaveMyCarToMyChiving(@LoginUser int userId, @Valid @RequestBody MyChivingTempSaveRequest myChivingSaveRequest) {
		MyChivingIdResponse response = myChivingService.temporarySaveMyCar(userId, myChivingSaveRequest);
		return GlobalResponse.ok(response);
	}
}
