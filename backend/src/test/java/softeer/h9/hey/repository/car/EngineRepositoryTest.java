package softeer.h9.hey.repository.car;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.Engine;

@SpringBootTest
@DisplayName("Engine Response")
class EngineRepositoryTest {

	@Autowired
	EngineRepository engineRepository;

	@Test
	@DisplayName("DB에 저장된 특정 모델에서 사용되는 엔진들을 조회한다.")
	void findByModel() {
		int modelId = 1;

		List<Engine> engines = engineRepository.findByModelId(modelId);

		assertThat(engines).hasSize(2);
	}
}
