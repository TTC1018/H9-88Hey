package softeer.h9.hey.dto.myChiving.response;

import lombok.Getter;

@Getter
public class BookmarkResponse {
	private boolean isMarked;

	private BookmarkResponse(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public static BookmarkResponse of(boolean isMarked) {
		return new BookmarkResponse(isMarked);
	}
}
