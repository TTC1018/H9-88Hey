package softeer.h9.hey.service.car;

import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.Engine;
import softeer.h9.hey.dto.car.response.EnginesResponse;
import softeer.h9.hey.repository.car.EngineRepository;

class EngineServiceTest {

	EngineRepository engineRepository = Mockito.mock(EngineRepository.class);
	EngineService engineService = new EngineService(engineRepository);

	@Test
	void searchEngineTest() {
		int modelId = 1;
		Engine engine1 = new Engine(1, 10000, modelId, "name", "imageURL", "descprition", "maximumPower",
			"maximumTorque");
		Engine engine2 = new Engine(2, 10000, modelId, "name", "imageURL", "descprition", "maximumPower",
			"maximumTorque");
		when(engineRepository.findByModelId(modelId)).thenReturn(List.of(engine1, engine2));

		EnginesResponse enginesResponse = engineService.findEnginesByModelId(modelId);
		List<Engine> engines = enginesResponse.getEngines();

		Assertions.assertThat(engines).hasSize(2);
		Assertions.assertThat(engines).containsExactly(engine1, engine2);
	}
}
