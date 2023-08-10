package softeer.h9.hey.repository.Car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.BodyType;

import java.util.List;

@SpringBootTest
@DisplayName("BodyType Repository 테스트")
class BodyTypeRepositoryTest {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @Test
    @DisplayName("model id에 해당하는 바디 타입을 모두 조회한다")
    void getBodyTypesByModelId() {
        int modelId = 1;

        List<BodyType> bodyTypes = bodyTypeRepository.findBodyTypesByModelId(modelId);

        Assertions.assertThat(bodyTypes).isNotNull();
    }
}
