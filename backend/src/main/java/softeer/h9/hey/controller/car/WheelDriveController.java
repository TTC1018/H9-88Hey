package softeer.h9.hey.controller.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.response.ModelImagesResponse;
import softeer.h9.hey.dto.car.response.WheelDriveResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.WheelDriveService;

@RequiredArgsConstructor
@RestController
public class WheelDriveController {

	private final WheelDriveService wheelDriveService;

	@GetMapping("/car/model/{modelId}/wheel-drive")
	public GlobalResponse<WheelDriveResponse> findWheelDrivesByModelId(@PathVariable("modelId") final int modelId) {
		WheelDriveResponse wheelDriveResponse = wheelDriveService.findWheelDrivesByModelId(modelId);
		return GlobalResponse.ok(wheelDriveResponse);
	}
}
