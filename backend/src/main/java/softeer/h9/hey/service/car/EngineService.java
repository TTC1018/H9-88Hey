package softeer.h9.hey.service.car;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import softeer.h9.hey.domain.car.Engine;
import softeer.h9.hey.dto.car.response.EnginesResponse;
import softeer.h9.hey.repository.car.EngineRepository;

@Service
@AllArgsConstructor
public class EngineService {

	private final EngineRepository engineRepository;

	public EnginesResponse findEnginesByModelId(int modelId) {
		List<Engine> engines = engineRepository.findByModelId(modelId);
		return EnginesResponse.of(engines);
	}
}
