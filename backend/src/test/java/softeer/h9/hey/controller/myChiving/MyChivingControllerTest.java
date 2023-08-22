package softeer.h9.hey.controller.myChiving;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class MyChivingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("최종 저장 시에 해당 저장건에 대한 마이카이빙 id를 반환해야 한다")
	@Transactional
	void saveMyCarToMyChiving() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/mychiving")
				.contentType("application/json")
				.header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content("{\n"
					+ "    \"myChivingId\": 481490775913230951,\n"
					+ "\t\"bodyType\": 1,\n"
					+ "\t\"wheelType\": 2,\n"
					+ "\t\"engine\": 2,\n"
					+ "\t\"trim\": 2,\n"
					+ "\t\"exteriorColor\": 3,\n"
					+ "\t\"interiorColor\": 5,\n"
					+ "\t\"selectOptions\":[\n"
					+ "\t\t\"DUP\", \"LX0010\", \"PA1\"\n"
					+ "\t]\n"
					+ "}"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.myChivingId").value(481490775913230951L));
	}

	@Test
	@DisplayName("최초 임시저장 시에 새로운 마이카이빙 id를 반환해야 한다")
	@Transactional
	void temporarySaveMyCarToMyChiving() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/mychiving/temp")
				.contentType("application/json")
				.header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content("{\n"
					+ "    \"myChivingId\": null,\n"
					+ "\t\"bodyType\": 1,\n"
					+ "\t\"wheelType\": 2,\n"
					+ "\t\"engine\": 2,\n"
					+ "\t\"trim\": 2,\n"
					+ "\t\"exteriorColor\": 3,\n"
					+ "\t\"interiorColor\": 5,\n"
					+ "\t\"selectOptions\":[\n"
					+ "\t\t\"DUP\", \"LX0010\", \"PA1\"\n"
					+ "\t]\n"
					+ "}"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.myChivingId").exists());
	}

	@Test
	@DisplayName("마이카이빙 조회시 200을 반환하고 myChivings를 반환해야 한다.")
	void findMyChiving() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/mychiving")
				.header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.param("offset", "1")
				.param("limit", "4"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.myChivings").exists());
	}
}
