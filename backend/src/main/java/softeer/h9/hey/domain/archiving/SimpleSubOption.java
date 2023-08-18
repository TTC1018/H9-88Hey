package softeer.h9.hey.domain.archiving;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleSubOption {
	private Set<String> subOptions = new HashSet<>();

	public void add(final String subOption) {
		subOptions.add(subOption);
	}
}
