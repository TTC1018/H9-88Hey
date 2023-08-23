package softeer.h9.hey.service.myChiving;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.controller.myChiving.ArchivingIdRequest;
import softeer.h9.hey.domain.archiving.Feed;
import softeer.h9.hey.dto.myChiving.request.BookmarkRequest;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
import softeer.h9.hey.dto.myChiving.response.FeedIdResponse;
import softeer.h9.hey.repository.myChiving.FeedRepository;

@DisplayName("Feed Service 테스트")
class FeedServiceTest {
	FeedRepository feedRepository = Mockito.mock(FeedRepository.class);
	FeedService feedService = new FeedService(feedRepository);

	@DisplayName("유저의 아카이빙 피드 북마크 여부를 확인한다.")
	@Test
	void hasBookmark() {
		int userId = 1;
		long feedId = 1234567890L;
		BookmarkRequest request = new BookmarkRequest(feedId);
		Feed feed = new Feed();
		feed.setFeedId(feedId);
		feed.setUserId(userId);
		feed.setIsMarked(true);
		List<Feed> response = List.of(feed);
		when(feedRepository.hasBookmark(userId, feedId))
			.thenReturn(response);

		BookmarkResponse result = feedService.hasBookmark(userId, request);

		assertTrue(result.isBookmark());
	}

	@DisplayName("유저가 아카이빙 피드의 북마크를 추가한다.")
	@Test
	void saveBookmark() {
		int userId = 1;
		long feedId = 1234567890L;
		int result = 1;
		ArchivingIdRequest request = ArchivingIdRequest.builder().feedId(feedId).build();
		when(feedRepository.save(userId, feedId))
			.thenReturn(result);

		FeedIdResponse response = feedService.saveFeed(userId, request);

		assertEquals(feedId, response.getFeedId());
	}
}
