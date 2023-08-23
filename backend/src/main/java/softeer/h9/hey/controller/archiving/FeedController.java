package softeer.h9.hey.controller.archiving;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
import softeer.h9.hey.dto.myChiving.response.FeedIdResponse;
import softeer.h9.hey.service.archiving.FeedService;

@RestController
@RequiredArgsConstructor
public class FeedController {

	private final FeedService service;

	@GetMapping("/feed/{feed_id}/bookmark")
	public GlobalResponse<BookmarkResponse> getBookmarkByArchivingId(
		@LoginUser int userId,
		@PathVariable(value = "feed_id") long feedId) {

		BookmarkResponse response = service.hasBookmark(userId, feedId);

		return GlobalResponse.ok(response);
	}

	@PostMapping("/feed/{feed_id}/bookmark")
	public GlobalResponse<FeedIdResponse> saveArchivingFeed(
		@LoginUser int userId,
		@PathVariable(value = "feed_id") long feedId) {

		FeedIdResponse result = service.saveFeed(userId, feedId);

		return GlobalResponse.ok(result);
	}

	@DeleteMapping("/feed/{feed_id}/bookmark")
	public GlobalResponse<?> deleteArchivingFeed(
		@LoginUser int userId,
		@PathVariable(value = "feed_id") long feedId) {

		service.deleteFeed(userId, feedId);

		return GlobalResponse.ok("정상적으로 삭제되었습니다.");
	}
}
