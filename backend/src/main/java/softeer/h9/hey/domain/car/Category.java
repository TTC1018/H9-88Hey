package softeer.h9.hey.domain.car;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Category {
	SELECT_OPTION("선택옵션"), N_PERFORMANCE("N Performance"), H_GENUINE("H Genuine");

	private final String name;

	Category(String name) {
		this.name = name;
	}

	public static Category findByName(String categoryName) {
		return Arrays.stream(Category.values())
			.filter(category -> category.name.equals(categoryName))
			.findFirst()
			.orElseThrow();
	}
}
