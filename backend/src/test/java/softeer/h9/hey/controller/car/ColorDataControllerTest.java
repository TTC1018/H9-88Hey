package softeer.h9.hey.controller.car;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("ColorDataController 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class ColorDataControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("특정 트림에 따른 외장, 내장 색상과 가능한 조합 색상들을 조회한다.")
	void findAllColorsByTrimId() throws Exception {
		int trimId = 1;

		mockMvc.perform(
				get("/car/color")
					.param("trim_id", String.valueOf(trimId)))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.statusCode").value(200),
				jsonPath("$.data.exteriorColors[0]").exists(),
				jsonPath("$.data.exteriorColors[0].id").value(1),
				jsonPath("$.data.exteriorColors[0].name").value("어비스 블랙 펄"),
				jsonPath("$.data.exteriorColors[0].carImagePath").value("https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/exterior/car/avisblack/"),
				jsonPath("$.data.exteriorColors[0].additionalPrice").value(0),
				jsonPath("$.data.exteriorColors[0].additionalPrice").value(0),
				jsonPath("$.data.exteriorColors[0].availableInteriorColors").isArray(),
				jsonPath("$.data.exteriorColors[0].availableInteriorColors[0]").value(1),
				jsonPath("$.data.exteriorColors[0].availableInteriorColors[1]").value(2)
			);
	}
}
