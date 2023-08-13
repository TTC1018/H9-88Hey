package softeer.h9.hey.controller.car;

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
class SelectOptionControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 선택 옵션 목록을 조회한다.")
	void getSelectOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/select-option")
					.param("carCode", "LXJJ8MST5"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].imageURL").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].imageURL").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].description").exists()
			);
	}

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 N Performance 목록을 조회한다.")
	void getNPerformanceOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/n-performance")
					.param("carCode", "LXJJ8MST5"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].imageURL").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].imageURL").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].description").exists()
			);
	}

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 H Genuine Accessory 목록을 조회한다")
	void getAvailableHGenuineOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/h-genuine-accessories")
					.param("carCode", "LXJJ7DCT5")
					.param("seletOption", "VI2"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].isAvailable").exists(),
				jsonPath("$.data.selectOptions[0].selectOption").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].imageURL").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].description").exists()
			);
	}
}
