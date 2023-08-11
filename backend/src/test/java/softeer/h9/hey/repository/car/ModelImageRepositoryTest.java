package softeer.h9.hey.repository.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("ModelImageRepository 테스트")
class ModelImageRepositoryTest {

	@Autowired
	private ModelImageRepository modelImageRepository;

	@Test
	@DisplayName("모델 아이디를 인자로 넘겼을 때 해당 모델에 해당하는 모든 대표 이미지들을 DB에서 가져와야 한다.")
	void findAllByModelId() {
		int modelId = 1;

		Assertions.assertThat(modelImageRepository.findAllByModelId(modelId)).hasSize(4);
	}

}
