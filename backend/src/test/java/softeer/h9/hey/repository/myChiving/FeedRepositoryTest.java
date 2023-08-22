package softeer.h9.hey.repository.myChiving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.archiving.Feed;

@SpringBootTest
@DisplayName("Feed Repository 테스트")
class FeedRepositoryTest {

	@Autowired
	private FeedRepository repository;

	@DisplayName("북마크가 존재하는지 확인한다.")
	@Test
	void hasBookmark() {
		Long feedId = 479893076429349285L;
		int userId = 1;

		Feed feed = repository.hasBookmark(userId, feedId);

		assertAll(
			() -> assertEquals(feedId, feed.getFeedId()),
			() -> assertEquals(userId, feed.getUserId()),
			() -> assertEquals(Boolean.TRUE, feed.isMarked())
		);
	}
}
