package softeer.h9.hey.domain.archiving;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Tags {
	private List<String> tags = new ArrayList<>();

	public static Tags of(List<String> tags) {
		return new Tags(tags);
	}

	public void add(final String tag) {
		tags.add(tag);
	}
}
