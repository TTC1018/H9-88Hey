package softeer.h9.hey.controller.myChiving;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.dto.myChiving.request.BookmarkRequest;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
import softeer.h9.hey.dto.myChiving.response.FeedIdResponse;
import softeer.h9.hey.service.myChiving.FeedService;

@RestController
@RequiredArgsConstructor
public class FeedController {

	private final FeedService service;

	@GetMapping("/mychiving/feed/bookmark")
	public GlobalResponse<BookmarkResponse> getBookmarkByArchivingId(
		@LoginUser int userId,
		BookmarkRequest bookmarkRequest) {

		BookmarkResponse response = service.hasBookmark(userId, bookmarkRequest);

		return GlobalResponse.ok(response);
	}

	@PostMapping("/mychiving/feed/bookmark")
	public GlobalResponse<FeedIdResponse> saveArchivingFeed(
		@LoginUser int userId,
		@RequestBody ArchivingIdRequest archivingIdRequest) {
		FeedIdResponse result = service.saveFeed(userId, archivingIdRequest);
		return GlobalResponse.ok(result);
	}

	@DeleteMapping("/mychiving/feed/bookmark")
	public GlobalResponse<FeedIdResponse> deleteArchivingFeed(
		@LoginUser int userId,
		@RequestBody ArchivingIdRequest archivingIdRequest) {
		FeedIdResponse result = service.deleteFeed(userId, archivingIdRequest);
		return GlobalResponse.ok(result);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public GlobalResponse<?> handlerEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		return GlobalResponse.error(HttpStatus.NOT_FOUND, "요청하신 데이터가 존재하지 않습니다.");
	}
}
