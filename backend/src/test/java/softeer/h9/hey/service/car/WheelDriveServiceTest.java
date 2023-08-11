package softeer.h9.hey.service.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.WheelDrive;
import softeer.h9.hey.dto.car.response.WheelDriveResponse;

@SpringBootTest
@DisplayName("WheelDriveService 테스트")
class WheelDriveServiceTest {

	@Autowired
	private WheelDriveService wheelDriveService;

	@Test
	@DisplayName("모델 아이디를 인자로 넘겨 휠 드라이브 조회 시 그 목록을 반환해야 한다.")
	void findWheelDrivesByModelId() {
		int modelId = 1;

		WheelDriveResponse wheelDriveResponse = wheelDriveService.findWheelDrivesByModelId(modelId);
		Assertions.assertThat(wheelDriveResponse.getWheelDrives()).hasSize(2);
	}
}
