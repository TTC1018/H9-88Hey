package softeer.h9.hey.domain.car;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum SelectOptionCategory {
	SELECT_OPTION("선택옵션"),
	N_PERFORMANCE("N Performance"),
	H_GENUINE("H Genuine Accessories");

	private final String name;

	SelectOptionCategory(String name) {
		this.name = name;
	}

	public static SelectOptionCategory findByName(String categoryName) {
		return Arrays.stream(SelectOptionCategory.values())
			.filter(selectOptionCategory -> selectOptionCategory.name.equals(categoryName))
			.findFirst()
			.orElseThrow();
	}
}
