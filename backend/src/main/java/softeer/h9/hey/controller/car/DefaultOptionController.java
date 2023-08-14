package softeer.h9.hey.controller.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.request.DefaultOptionRequest;
import softeer.h9.hey.dto.car.response.DefaultOptionsResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.DefaultOptionService;

@RestController
@RequiredArgsConstructor
public class DefaultOptionController {

	private final DefaultOptionService defaultOptionService;

	@GetMapping("/car/default-option")
	public GlobalResponse<DefaultOptionsResponse> findAllByCarCode(
		DefaultOptionRequest defaultOptionRequest) {
		DefaultOptionsResponse defaultOptionsResponse = defaultOptionService.findAllByCarCode(defaultOptionRequest);
		return GlobalResponse.ok(defaultOptionsResponse);
	}
}
