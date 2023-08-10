package softeer.h9.hey.service.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softeer.h9.hey.dto.car.response.BodyTypeResponse;
import softeer.h9.hey.repository.Car.BodyTypeRepository;

@Service
@RequiredArgsConstructor
public class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;
    public BodyTypeResponse findAllByModelId(final int modelId) {
        return BodyTypeResponse.of(bodyTypeRepository.findAllByModelId(modelId));
    }
}
