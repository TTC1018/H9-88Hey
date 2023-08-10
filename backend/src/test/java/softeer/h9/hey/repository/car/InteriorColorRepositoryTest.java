package softeer.h9.hey.repository.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.InteriorColor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("ExteriorColorRepository Test")
class InteriorColorRepositoryTest {

    @Autowired
    private InteriorColorRepository interiorColorRepository;

    @Test
    @DisplayName("DB에 저장된 특정 트림의 외장 색상들을 조회한다.")
    void findAllByTrimId() {
        int trimId = 4;

        List<InteriorColor> interiorColors = interiorColorRepository.findAllByTrimId(trimId);

        assertAll(
                () -> assertNotNull(interiorColors),
                () -> assertEquals(4, interiorColors.size()),
                () -> interiorColors.forEach(exteriorColor ->
                        assertEquals(trimId, exteriorColor.getTrimId())
                )
        );
    }
}
