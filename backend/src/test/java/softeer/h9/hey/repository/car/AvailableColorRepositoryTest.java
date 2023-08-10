package softeer.h9.hey.repository.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.AvailableColor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("AvailableColor Repository 테스트")
public class AvailableColorRepositoryTest {

    @Autowired
    private AvailableColorRepository availableColorRepository;

    @Test
    @DisplayName("trim id에 따른 조합 ")
    void findAllByTrimId() {
        int trimId = 3;

        List<AvailableColor> availableColors = availableColorRepository.findAllByTrimId(trimId);

        assertAll(
                () -> assertNotNull(availableColors),
                () -> assertEquals(18, availableColors.size()),
                () -> availableColors.forEach(availableColor ->
                        assertEquals(trimId, availableColor.getTrimId())
                )
        );
    }
}
