package softeer.h9.hey.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.dto.car.response.EnginesResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequiredArgsConstructor
public class ModelImageController {

    private final ModelImageService modelImageService;

    @GetMapping("/car/model/{modelId}/Image")
    public GlobalResponse<ModelImageResponse> findImageByModelId(@PathVariable final int modelId) {
        ModelImageResponse modelResponse = modelImageService.findImageByModelId(modelId);
        return GlobalResponse.ok(ModelImageResponse);
    }
}
