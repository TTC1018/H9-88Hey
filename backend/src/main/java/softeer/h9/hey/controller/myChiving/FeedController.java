package softeer.h9.hey.controller.myChiving;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
import softeer.h9.hey.dto.myChiving.response.FeedIdResponse;
import softeer.h9.hey.service.myChiving.FeedService;

@RestController
@RequiredArgsConstructor
public class FeedController {

	private final FeedService service;

	@GetMapping("/archiving/{feed_id}/bookmark")
	public GlobalResponse<BookmarkResponse> getBookmarkByArchivingId(
		@LoginUser final int userId,
		@PathVariable final Long feed_id) {

		System.out.println("userId = " + userId);
		System.out.println("feed_id = " + feed_id);
		BookmarkResponse response = service.hasBookmark(userId, feed_id);

		return GlobalResponse.ok(response);
	}

	@PostMapping("/archiving/{feed_id}/bookmark")
	public GlobalResponse<FeedIdResponse> saveArchivingFeed(
		@LoginUser final int userId,
		@PathVariable final long feed_id) {
		FeedIdResponse result = service.saveFeed(userId, feed_id);
		return GlobalResponse.ok(result);
	}

	@DeleteMapping("/archiving/{feed_id}/bookmark")
	public GlobalResponse<FeedIdResponse> deleteArchivingFeed(
		@LoginUser final int userId,
		@PathVariable final long feed_id) {
		FeedIdResponse result = service.deleteFeed(userId, feed_id);
		return GlobalResponse.ok(result);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public GlobalResponse<?> handlerEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		return GlobalResponse.error(HttpStatus.NOT_FOUND, "요청하신 데이터가 존재하지 않습니다.");
	}
}
