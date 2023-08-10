package softeer.h9.hey.service.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softeer.h9.hey.domain.car.CarInfo;
import softeer.h9.hey.dto.car.request.CarCodeRequest;
import softeer.h9.hey.dto.car.response.CarCodeResponse;
import softeer.h9.hey.repository.car.CarInfoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarInfoService {

    private final CarInfoRepository carInfoRepository;

    public CarCodeResponse getCarCode(CarCodeRequest carCodeRequest) {
        int trimId = carCodeRequest.getTrimId();
        int engineId = carCodeRequest.getEngineId();
        int bodyTypeId = carCodeRequest.getBodyTypeId();
        int wheelDriveId = carCodeRequest.getWheelDriveId();

        Optional<CarInfo> optionalCarInfo = carInfoRepository.findBy(trimId, engineId, bodyTypeId, wheelDriveId);
        CarInfo carInfo = optionalCarInfo.orElseThrow(() -> new RuntimeException("Not Found That CarCode"));
        return CarCodeResponse.of(carInfo.getCarCode());
    }
}
