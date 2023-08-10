package softeer.h9.hey.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.service.car.BodyTypeService;
import softeer.h9.hey.dto.car.response.BodyTypesResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class BodyTypeController {

    private final BodyTypeService bodyTypeService;

    @GetMapping("/model/{modelId}/body-type")
    public GlobalResponse<BodyTypesResponse> findBodyTypesByModelId(@PathVariable final int modelId) {
        BodyTypesResponse response = bodyTypeService.findBodyTypesByModelId(modelId);
        return GlobalResponse.ok(response);
    }
}
