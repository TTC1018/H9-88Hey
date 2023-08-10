package softeer.h9.hey.repository.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softeer.h9.hey.domain.car.CarInfo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DisplayName("CarInfo Repository 테스트")
class CarInfoRepositoryTest {

    @Autowired
    private CarInfoRepository carInfoRepository;

    @Test
    @DisplayName("trim id, engine id, bodyType id, wheelDrive id를 통해 Car Info를 조회한다.")
    void findCarInfoTest() {
        int trimId = 2;
        int engineId = 2;
        int bodyTypeId = 1;
        int wheelDriveId = 1;

        Optional<CarInfo> optionalCarInfo = carInfoRepository.findBy(trimId, engineId, bodyTypeId, wheelDriveId);

        assertTrue(optionalCarInfo.isPresent());
        assertNotNull(optionalCarInfo.get().getCarCode());
    }

    @Test
    @DisplayName("조합에 맞는 CarInfo가 없는 경우 Optional에 Null을 넣어 반환한다.")
    void findCarInfo() {
        int trimId = 100;
        int engineId = 2;
        int bodyTypeId = 1;
        int wheelDriveId = 1;

        Optional<CarInfo> optionalCarInfo = carInfoRepository.findBy(trimId, engineId, bodyTypeId, wheelDriveId);

        assertTrue(optionalCarInfo.isEmpty());
    }
}
