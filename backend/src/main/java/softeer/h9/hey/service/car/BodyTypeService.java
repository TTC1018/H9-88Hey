package softeer.h9.hey.service.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softeer.h9.hey.dto.car.response.BodyTypesResponse;
import softeer.h9.hey.repository.Car.BodyTypeRepository;

@Service
@RequiredArgsConstructor
public class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypesResponse findBodyTypesByModelId(final int modelId) {
        return BodyTypesResponse.of(bodyTypeRepository.findBodyTypesByModelId(modelId));
    }
}
