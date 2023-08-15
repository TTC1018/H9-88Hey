package softeer.h9.hey.controller.car;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("DefaultOptionController 테스트")
class DefaultOptionControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("car code에 해당하는 기본 옵션들을 조회한다.")
	void findAllByCarCode() throws Exception {
		mockMvc.perform(
				get("/car/default-option")
					.param("car_code", "LXJJ8MST5"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.defaultOptions").exists(),
				jsonPath("$.data.defaultOptions[0].category").exists(),
				jsonPath("$.data.defaultOptions[0].subOptions").exists(),
				jsonPath("$.data.defaultOptions[0].subOptions[0].name").exists(),
				jsonPath("$.data.defaultOptions[0].subOptions[0].imageUrl").exists(),
				jsonPath("$.data.defaultOptions[0].subOptions[0].description").exists()
			);
	}
}
