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
@DisplayName("선택 옵션 controller 테스트")
class SelectOptionControllerTest {

	@Autowired
	MockMvc mockMvc;

	@DisplayName("model id에 따라 모든 선택 옵션을 조회한다.")
	@Test
	void getAllNormalSelectOption() throws Exception{
		mockMvc.perform(
				get("/car/select-options")
					.param("model_id", "1"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").isArray(),
				jsonPath("$.data.selectOptions[0].id").isString(),
				jsonPath("$.data.selectOptions[0].name").isString(),
				jsonPath("$.data.selectOptions[0].category").isString(),
				// 존재하면 안되는 것들.
				jsonPath("$.data.selectOptions[0].imageUrl").doesNotExist(),
				jsonPath("$.data.selectOptions[0].additionalPrice").doesNotExist(),
				jsonPath("$.data.selectOptions[0].subOptions").doesNotExist()
			);
	}

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 선택 옵션 목록을 조회한다.")
	void getSelectOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/select-option")
					.param("car_code", "LXJJ8MST5"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].imageUrl").exists(),
				jsonPath("$.data.selectOptions[0].additionalPrice").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].imageUrl").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].description").exists()
			);
	}

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 N Performance 목록을 조회한다.")
	void getNPerformanceOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/n-performance")
					.param("car_code", "LXJJ8MST5"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].imageUrl").exists(),
				jsonPath("$.data.selectOptions[0].additionalPrice").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].imageUrl").exists(),
				jsonPath("$.data.selectOptions[0].subOptions[0].description").exists()
			);
	}

	@Test
	@DisplayName("car code를 통해 선택할 수 있는 H Genuine Accessory 목록을 조회한다")
	void getAvailableHGenuineOptionTest() throws Exception {
		mockMvc.perform(
				get("/car/h-genuine-accessories")
					.param("car_code", "LXJJ7DCT5")
					.param("select_option", "VI2"))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.selectOptions").exists(),
				jsonPath("$.data.selectOptions[0].isAvailable").exists(),
				jsonPath("$.data.selectOptions[0].selectOption").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.additionalPrice").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0]").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].id").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].name").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].imageUrl").exists(),
				jsonPath("$.data.selectOptions[0].selectOption.subOptions[0].description").exists()
			);
	}
}
