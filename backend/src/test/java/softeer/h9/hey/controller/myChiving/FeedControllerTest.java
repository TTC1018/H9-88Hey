package softeer.h9.hey.controller.myChiving;

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

	@BeforeEach
	void setUp() throws Exception {
		long feedId = 123;
		String requestBody = "{\"feed_id\":" + feedId + "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));
	}

	@DisplayName("북마크 여부를 확인한다.")
	@Test
	void getBookmarkByArchivingId() throws Exception {
		long feedId = 123;

		mockMvc.perform(MockMvcRequestBuilders.get("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.param("feed_id", String.valueOf(feedId)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.bookmark").value(true));
	}

	@DisplayName("유저가 아카이빙 ID를 통해 해당하는 북마크를 저장한다.")
	@Test
	void saveFeedByArchivingId() throws Exception {
		long feedId = 12345;
		String requestBody = "{\"feed_id\":" + feedId + "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));

		mockMvc.perform(MockMvcRequestBuilders.get("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.param("feed_id", String.valueOf(feedId)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.bookmark").value(true));
	}

	@DisplayName("유저가 아카이빙 ID를 통해 해당하는 북마크를 해제한다.")
	@Test
	void deleteFeedByArchivingId() throws Exception {
		long feedId = 123;
		String requestBody = "{\"feed_id\":" + feedId + "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));

		mockMvc.perform(MockMvcRequestBuilders.delete("/mychiving/feed/bookmark")
				.contentType("application/json")
				.header("Authorization",
					"Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ")
				.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.feedId").value(feedId));
	}
}
