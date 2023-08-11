package softeer.h9.hey.controller.car;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("WheelDriveController 테스트")
class WheelDriveControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("/car/model/{modelId}/wheel-drive로 요청을 보낼 시 해당 모델 아이디와 연관된 wheelDrive 목록을 반환해야 한다.")
	void findWheelDriveByModelId() throws Exception {
		int modelId = 1;

		mockMvc.perform(
				get("/car/model/" + modelId + "/wheel-drive")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.wheelDrives").exists(),
				jsonPath("$.data.wheelDrives[0]").exists(),
				jsonPath("$.data.wheelDrives[1]").exists()
			);
	}

}
