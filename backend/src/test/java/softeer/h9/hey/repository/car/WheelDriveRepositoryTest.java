package softeer.h9.hey.repository.car;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.WheelDrive;

@SpringBootTest
@DisplayName("WheelDriveRepository 테스트")
class WheelDriveRepositoryTest {

	@Autowired
	private WheelDriveRepository wheelDriveRepository;

	@Test
	@DisplayName("모델 아이디를 인자로 넘겨 요청하면 DB에서 모델이 가지고 있는 WheelDrive를 제공해야 한다.")
	void findAllByModelId() {
		int modelId = 1;

		List<WheelDrive> wheelDrives = wheelDriveRepository.findAllByModelId(modelId);

		assertThat(wheelDrives).hasSize(2);

	}
}
