package softeer.h9.hey.service.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import softeer.h9.hey.domain.car.Trim;
import softeer.h9.hey.domain.car.TrimFeature;
import softeer.h9.hey.dto.car.response.TrimsResponse;
import softeer.h9.hey.repository.car.TrimRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("트림 조회 서비스 테스트")
public class TrimServiceTest {

    TrimRepository trimRepository = Mockito.mock(TrimRepository.class);
    TrimService trimService = new TrimService(trimRepository);


    @DisplayName("특정 모델에 적용할 수 있는 트림들을 조회한다.")
    @Test
    void searchTrimTest() {
        int modelId = 1;
        when(trimRepository.findTrimsByModelId(modelId)).thenReturn(getTrimFixture());

        TrimsResponse trimsResponse = trimService.findTrimsByModelId(modelId);
        List<Trim> trims = trimsResponse.getTrims();

        assertThat(trims).hasSize(2);
        for (Trim trim : trims) {
            assertThat(trim.getTrimFeatures()).hasSize(3);
        }
    }

    private List<Trim> getTrimFixture() {
        Trim trim1 = new Trim(1, "trim1", 100);
        trim1.addTrimFeature(new TrimFeature("트림1 특징1", "url11"));
        trim1.addTrimFeature(new TrimFeature("트림1 특징2", "url12"));
        trim1.addTrimFeature(new TrimFeature("트림1 특징3", "url13"));

        Trim trim2 = new Trim(2, "trim2", 200);
        trim2.addTrimFeature(new TrimFeature("트림2 특징1", "url21"));
        trim2.addTrimFeature(new TrimFeature("트림2 특징2", "url22"));
        trim2.addTrimFeature(new TrimFeature("트림2 특징3", "url23"));

        return List.of(trim1, trim2);
    }
}
