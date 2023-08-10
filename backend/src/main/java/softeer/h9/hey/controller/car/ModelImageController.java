package softeer.h9.hey.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.dto.car.response.EnginesResponse;
import softeer.h9.hey.dto.car.response.ModelImagesResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.ModelImageService;

@RestController
@RequiredArgsConstructor
public class ModelImageController {

    private final ModelImageService modelImageService;

    @GetMapping("/car/model/{modelId}/Image")
    public GlobalResponse<ModelImagesResponse> findModelImageUrlsByModelId(@PathVariable final int modelId) {
        ModelImagesResponse modelResponse = modelImageService.findModelImageUrlsByModelId(modelId);
        return GlobalResponse.ok(modelResponse);
    }
}
