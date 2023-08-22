package softeer.h9.hey.controller.myChiving;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.annotation.LoginUser;
import softeer.h9.hey.dto.global.response.GlobalResponse;
import softeer.h9.hey.dto.myChiving.request.BookmarkRequest;
import softeer.h9.hey.dto.myChiving.response.BookmarkResponse;
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
}
