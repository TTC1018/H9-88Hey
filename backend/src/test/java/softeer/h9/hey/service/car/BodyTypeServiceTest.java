package softeer.h9.hey.service.car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.BodyType;
import softeer.h9.hey.dto.car.response.BodyTypeResponse;
import softeer.h9.hey.repository.Car.BodyTypeRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("BodyType Service 테스트")
class BodyTypeServiceTest {

    private final BodyTypeRepository bodyTypeRepository = Mockito.mock(BodyTypeRepository.class);
    private final BodyTypeService bodyTypeService = new BodyTypeService(bodyTypeRepository);

    @Test
    @DisplayName("model id를 받아 구동 방식을 조회한다")
    void findAllBodyTypeByModelId() {
        int modelId = 1;
        BodyType bodyType1 = new BodyType(modelId, "7인승", "url1", 12300, "설명");
        BodyType bodyType2 = new BodyType(modelId, "8인승", "url2", 32100, "설명2");
        when(bodyTypeRepository.findBodyTypesByModelId(modelId)).thenReturn(List.of(bodyType1, bodyType2));

        BodyTypeResponse response = bodyTypeService.findBodyTypesByModelId(modelId);
        List<BodyType> bodyTypes = response.getBodyTypes();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, bodyTypes.size()),
                () -> Assertions.assertTrue(bodyTypes.containsAll(List.of(bodyType1, bodyType2)))
        );
    }
}
