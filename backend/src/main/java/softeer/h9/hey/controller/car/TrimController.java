package softeer.h9.hey.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.dto.car.response.TrimsResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.TrimService;

@RequiredArgsConstructor
@RestController
public class TrimController {

    private final TrimService trimService;

    @GetMapping("/car/model/{model_id}/trim")
    public GlobalResponse<TrimsResponse> getTrim(@PathVariable("model_id") int modelId) {
        TrimsResponse trimsResponse = trimService.findTrimsByModelId(modelId);
        return GlobalResponse.ok(trimsResponse);
    }
}
