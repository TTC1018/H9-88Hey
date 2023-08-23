package softeer.h9.hey.repository.myChiving;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import softeer.h9.hey.domain.archiving.Feed;

@SpringBootTest
@DisplayName("Feed Repository 테스트")
@Transactional
class FeedRepositoryTest {

	@Autowired
	private FeedRepository repository;

	private final int testUserId = 0;
	private final long testFeedId = 12345L;

	@BeforeEach
	void setUp() {
		repository.save(testUserId, testFeedId);
	}

	@DisplayName("북마크가 존재하는지 확인한다.")
	@Test
	void hasBookmark() {
		List<Feed> feeds = repository.hasBookmark(testUserId, testFeedId);

		assertEquals(1, feeds.size());
	}

	@Test
	@DisplayName("유저가 아카이빙 피드를 북마크한다.")
	void save() {
		long feedId = 1234567890L;
		int userId = 1;

		int save = repository.save(userId, feedId);
		List<Feed> feeds = repository.hasBookmark(userId, userId);

		assertEquals(1, save);
		assertTrue(feeds.size() > 0);
	}

	@Test
	@DisplayName("유저가 아카이빙 피드를 북마크 해제한다.")
	void delete() {
		int result = repository.deleteBookmark(testUserId, testFeedId);
		assertTrue(result > 0);
	}
}
