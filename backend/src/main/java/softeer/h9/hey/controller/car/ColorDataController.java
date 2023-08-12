package softeer.h9.hey.controller.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.response.ColorDataResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.ColorDataService;

@RestController
@RequiredArgsConstructor
public class ColorDataController {

	private final ColorDataService colorDataService;

	@GetMapping("/car/color")
	public GlobalResponse<ColorDataResponse> findAvailableColorsByTrimId(@RequestParam final int trimId) {
		ColorDataResponse colorDataResponse = colorDataService.findAvailableColorsByTrimId(trimId);
		return GlobalResponse.ok(colorDataResponse);
	}
}
