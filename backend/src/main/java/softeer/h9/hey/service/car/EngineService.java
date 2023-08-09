package softeer.h9.hey.service.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softeer.h9.hey.domain.car.Engine;
import softeer.h9.hey.dto.car.response.EnginesResponse;
import softeer.h9.hey.repository.car.EngineRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineService {

    private final EngineRepository engineRepository;

    public EnginesResponse findEnginesByModelId(int modelId) {
        List<Engine> engines = engineRepository.findByModelId(modelId);
        return EnginesResponse.of(engines);
    }
}
