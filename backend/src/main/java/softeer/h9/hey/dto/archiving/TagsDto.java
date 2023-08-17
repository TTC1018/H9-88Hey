package softeer.h9.hey.dto.archiving;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagsDto {
	private List<String> tags;
}
