package softeer.h9.hey.service.car;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.CarInfo;
import softeer.h9.hey.dto.car.request.CarCodeRequest;
import softeer.h9.hey.dto.car.response.CarCodeResponse;
import softeer.h9.hey.repository.car.CarInfoRepository;

@DisplayName("CarInfo Service 테스트")
class CarInfoServiceTest {
	private final CarInfoRepository carInfoRepository = Mockito.mock(CarInfoRepository.class);
	private final CarInfoService carInfoService = new CarInfoService(carInfoRepository);

	@Test
	@DisplayName("특정 구성을 갖는 CarInfo로부터 carCode를 가져온다.")
	void findCarCodeTest() {
		String expectedCarCode = "LXJJ8MAA5";
		CarCodeRequest carCodeRequest = new CarCodeRequest(2, 2, 2, 2);

		when(carInfoRepository.findBy(2, 2, 2, 2))
			.thenReturn(Optional.of(new CarInfo(expectedCarCode, 2, 2, 2, 2)));

		CarCodeResponse carCodeResponse = carInfoService.getCarCode(carCodeRequest);
		String carCode = carCodeResponse.getCarCode();

		Assertions.assertThat(carCode).isEqualTo(expectedCarCode);
	}

	@Test
	@DisplayName("조합에 맞는 CarInfo가 없는 경우 예외를 던진다.")
	void findCarCodeFailTest() {
		CarCodeRequest carCodeRequest = new CarCodeRequest(1212, 2, 2, 2);

		when(carInfoRepository.findBy(1212, 2, 2, 2))
			.thenReturn(Optional.empty());

		Assertions.assertThatThrownBy(() -> carInfoService.getCarCode(carCodeRequest))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Not Found That CarCode");
	}
}
