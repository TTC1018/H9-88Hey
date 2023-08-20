package softeer.h9.hey.controller.car;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Tag Controller 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class TagControllerTest {

	@Autowired
	MockMvc mockMvc;

	@DisplayName("선택옵션 id에 따라 상위 limit개의 태그를 조회한다.")
	@Test
	void getTopTagBySelectOption() throws Exception {
		String id = "HSS";
		int limit = 5;

		mockMvc.perform(
				get("/car/tag/select-option")
					.param("id", id)
					.param("limit", String.valueOf(limit))
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.statusCode").value(200),
				jsonPath("$.data.tags").exists(),
				jsonPath("$.data.tags").isArray()
			);
	}

	@DisplayName("외장색상 id에 따라 상위 limit개의 태그를 조회한다.")
	@Test
	void getTopTagByExteriorColor() throws Exception {
		int id = 3;
		int limit = 5;

		mockMvc.perform(
				get("/car/tag/exterior-color")
					.param("id", String.valueOf(id))
					.param("limit", String.valueOf(limit))
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.statusCode").value(200),
				jsonPath("$.data.tags").exists(),
				jsonPath("$.data.tags").isArray()
			);
	}
}
