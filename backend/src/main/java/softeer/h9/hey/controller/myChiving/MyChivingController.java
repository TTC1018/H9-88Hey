package softeer.h9.hey.controller.myChiving;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.myChiving.request.MyChivingRequest;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
import softeer.h9.hey.dto.myChiving.response.MyChivingsResponse;
import softeer.h9.hey.exception.myChiving.DeletionFailException;
import softeer.h9.hey.exception.myChiving.InValidAccessException;
import softeer.h9.hey.service.myChiving.MyChivingService;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequiredArgsConstructor
public class MyChivingController {

	private final MyChivingService myChivingService;

	@GetMapping("/mychiving")
	public GlobalResponse<MyChivingsResponse> findMyChivings(@LoginUser int userId,
		final MyChivingRequest myChivingRequest) {
		MyChivingsResponse myChivingsResponse = myChivingService.findMyChivings(userId, myChivingRequest);
		return GlobalResponse.ok(myChivingsResponse);
	}

	@PostMapping("/mychiving")
	public GlobalResponse<MyChivingIdResponse> saveMyCarToMyChiving(@LoginUser int userId,
		@Valid @RequestBody MyChivingSaveRequest myChivingSaveRequest) {
		MyChivingIdResponse response = myChivingService.saveMyCar(userId, myChivingSaveRequest);
		return GlobalResponse.ok(response);
	}

	@PostMapping("/mychiving/temp")
	public GlobalResponse<MyChivingIdResponse> temporarySaveMyCarToMyChiving(@LoginUser int userId,
		@Valid @RequestBody MyChivingTempSaveRequest myChivingSaveRequest) {
		MyChivingIdResponse response = myChivingService.temporarySaveMyCar(userId, myChivingSaveRequest);
		return GlobalResponse.ok(response);
	}

	@DeleteMapping("/mychiving/{mychiving_id}")
	public GlobalResponse<String> deleteMyChiving(@LoginUser int userId,
		@PathVariable(value = "mychiving_id") long myChivingId) {
		String result = myChivingService.deleteMyChivingByMyChivingIdAndUserId(userId, myChivingId);
		return GlobalResponse.ok(result);
	}

	@ExceptionHandler
	public ResponseEntity<?> handlerException(InValidAccessException e) {
		GlobalResponse<?> errorResponse = GlobalResponse.error(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<?> handlerException(DeletionFailException e) {
		GlobalResponse<?> errorResponse = GlobalResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
