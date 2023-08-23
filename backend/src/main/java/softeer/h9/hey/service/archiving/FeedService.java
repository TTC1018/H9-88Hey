package softeer.h9.hey.service.archiving;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.archiving.Feed;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
import softeer.h9.hey.dto.myChiving.response.FeedIdResponse;
import softeer.h9.hey.repository.archiving.FeedRepository;

@Service
@RequiredArgsConstructor
public class FeedService {
	private final FeedRepository repository;

	public BookmarkResponse hasBookmark(final int userId, final long feedId) {
		List<Feed> result = repository.hasBookmark(userId, feedId);
		if (result.size() > 0) {
			return BookmarkResponse.of(true);
		}
		return BookmarkResponse.of(false);
	}

	public FeedIdResponse saveFeed(final int userId, final long feedId) {
		int result = repository.save(userId, feedId);

		return FeedIdResponse.of(feedId);
	}

	public FeedIdResponse deleteFeed(final int userId, final long feedId) {
		int result = repository.deleteBookmark(userId, feedId);

		return FeedIdResponse.of(feedId);
	}
}
