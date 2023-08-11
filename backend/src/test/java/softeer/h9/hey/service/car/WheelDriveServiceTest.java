package softeer.h9.hey.service.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.car.WheelDrive;

@SpringBootTest
@DisplayName("WheelDriveService 테스트")
class WheelDriveServiceTest {

	@Autowired
	private WheelDriveService wheelDriveService;

	@Test
	@DisplayName("모델 아이디를 인자로 넘겨 휠 드라이브 조회 시 그 목록을 반환해야 한다.")
	void findWheelDrivesByModelId() {
		List<WheelDrive> wheelDrives = wheelDriveService.findWheelDrivesByModelId();
		Assertions.assertThat(wheelDrives).hasSize(2);
	}
}
