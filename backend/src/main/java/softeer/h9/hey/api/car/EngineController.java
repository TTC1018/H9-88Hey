package softeer.h9.hey.api.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.application.car.EngineService;
import softeer.h9.hey.dto.GlobalResponse;
import softeer.h9.hey.dto.car.response.EnginesResponse;

@RequiredArgsConstructor
@RestController
public class EngineController {

    private final EngineService engineService;

    @GetMapping("/car/model/{modelId}/trim")
    public GlobalResponse<EnginesResponse> findEnginesByModelId(@PathVariable final int modelId) {
        EnginesResponse enginesResponse = engineService.findEnginesByModelId(modelId);
        return GlobalResponse.ok(enginesResponse);
    }
}
