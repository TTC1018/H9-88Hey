package softeer.h9.hey.controller.archiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
@Transactional
@DisplayName("Feed Controller 테스트")
class FeedControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private long feedId = 123;

	@BeforeEach
	void setUp() throws Exception {
		String postUrl = "/user/archiving/" + feedId + "/bookmark";

		mockMvc.perform(MockMvcRequestBuilders.post(postUrl)
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));
	}

	@DisplayName("북마크 여부를 확인한다.")
	@Test
	void getBookmarkByArchivingId() throws Exception {
		String postUrl = "/user/archiving/" + feedId + "/bookmark";

		mockMvc.perform(MockMvcRequestBuilders.get(postUrl)
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.bookmark").value(true));
	}

	@DisplayName("유저가 아카이빙 ID를 통해 해당하는 북마크를 저장한다.")
	@Test
	void saveFeedByArchivingId() throws Exception {
		String postUrl = "/user/archiving/" + feedId + "/bookmark";

		mockMvc.perform(MockMvcRequestBuilders.post(postUrl)
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));

		mockMvc.perform(MockMvcRequestBuilders.get(postUrl)
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.bookmark").value(true));
	}

	@DisplayName("유저가 아카이빙 ID를 통해 해당하는 북마크를 해제한다.")
	@Test
	void deleteFeedByArchivingId() throws Exception {
		String postUrl = "/user/archiving/" + feedId + "/bookmark";

		mockMvc.perform(MockMvcRequestBuilders.post(postUrl)
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));

		mockMvc.perform(MockMvcRequestBuilders.delete(postUrl)
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
