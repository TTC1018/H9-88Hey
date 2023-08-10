package softeer.h9.hey.repository.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.Trim;
import softeer.h9.hey.domain.car.TrimFeature;

import java.util.List;

@DisplayName("트림 DB 조회 테스트")
@SpringBootTest
class TrimRepositoryTest {

    @Autowired
    TrimRepository trimRepository;

    @Test
    @DisplayName("DB에 저장된 특정 모델에서 적용할 수 있는 트림 리스트를 조회한다.")
    void findTrimsByModel() {
        int modelId = 1;

        List<Trim> trims = trimRepository.findTrimsByModelId(modelId);

        Assertions.assertThat(trims).hasSize(4);
    }

    @Test
    @DisplayName("각각의 트림들은 트림 특징을 3개씩 갖는다.")
    void findTrimFeatureByModel() {
        int modelId = 1;

        List<Trim> trims = trimRepository.findTrimsByModelId(modelId);

        for (Trim trim : trims) {
            List<TrimFeature> trimFeatures = trim.getTrimFeatures();
            Assertions.assertThat(trimFeatures).hasSize(3);
        }
    }
}
