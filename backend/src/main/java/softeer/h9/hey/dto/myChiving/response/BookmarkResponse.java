package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class BookmarkResponse {
	private boolean bookmark;

	private BookmarkResponse(boolean bookmark) {
		this.bookmark = bookmark;
	}

	public static BookmarkResponse of(boolean isMarked) {
		return new BookmarkResponse(isMarked);
	}
}
