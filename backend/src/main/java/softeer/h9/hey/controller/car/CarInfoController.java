package softeer.h9.hey.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import softeer.h9.hey.dto.car.request.CarCodeRequest;
import softeer.h9.hey.dto.car.response.CarCodeResponse;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.service.car.CarInfoService;

@RequiredArgsConstructor
@RestController
public class CarInfoController {

    private final CarInfoService carInfoService;

    @GetMapping("/car/car-code")
    public GlobalResponse<CarCodeResponse> getCarCode(CarCodeRequest carCodeRequest) {
        CarCodeResponse carCodeResponse = carInfoService.getCarCode(carCodeRequest);
        return GlobalResponse.ok(carCodeResponse);
    }
}
