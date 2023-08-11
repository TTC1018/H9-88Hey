package softeer.h9.hey.controller.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.response.ModelImagesResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.ModelImageService;

@RestController
@RequiredArgsConstructor
public class ModelImageController {

	private final ModelImageService modelImageService;

	@GetMapping("/car/model/{modelId}/image")
	public GlobalResponse<ModelImagesResponse> findModelImageUrlsByModelId(@PathVariable final int modelId) {
		ModelImagesResponse modelResponse = modelImageService.findModelImageUrlsByModelId(modelId);
		return GlobalResponse.ok(modelResponse);
	}
}
