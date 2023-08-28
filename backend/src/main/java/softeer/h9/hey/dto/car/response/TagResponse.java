package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponse {
	final List<String> tags;

	public static TagResponse from(List<String> tags) {
		return new TagResponse(tags);
	}
}
