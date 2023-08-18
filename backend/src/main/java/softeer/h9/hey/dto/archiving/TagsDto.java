package softeer.h9.hey.dto.archiving;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TagsDto {
	private final List<String> tags;

	public static TagsDto of(List<String> tags) {
		return new TagsDto(tags);
	}
}
