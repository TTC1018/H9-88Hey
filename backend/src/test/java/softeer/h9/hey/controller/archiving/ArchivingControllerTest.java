package softeer.h9.hey.controller.archiving;

import static org.hamcrest.Matchers.*;
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
@DisplayName("Archiving Controller 테스트")
class ArchivingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@DisplayName("선택옵션, 모델에 따른 페이지네이션된 아카이빙을 조회한다.")
	@Test
	void findArchivingByModelIdAndOptions() throws Exception {
		int modelId = 1;
		int offset = 1;
		int limit = 8;
		String selectOption1 = "2VS";
		String selectOption2 = "HSS";
		mockMvc.perform(
				get("/archiving")
					.param("model_id", String.valueOf(modelId))
					.param("offset", String.valueOf(offset))
					.param("limit", String.valueOf(limit))
					.param("select_option", selectOption1)
					.param("select_option", selectOption2)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.data.archivings").exists(),
				content().string(containsString("2VS")),
				content().string(containsString("HSS"))
			);
	}

	@DisplayName("특정 아카이빙 id에 따른 상세 아카이빙 페이지를 조회한다.")
	@Test
	void findArchivingDetailById() throws Exception {
		long id = 479893076429349314L;
		String url = "/archiving/" + id;
		mockMvc.perform(
				get(url)
					.param("id", String.valueOf(id))
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpectAll(
				jsonPath("$.statusCode").value(200),
				jsonPath("$.data.feedId").value(id)
			);
	}
}
