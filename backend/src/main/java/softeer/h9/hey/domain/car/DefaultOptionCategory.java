package softeer.h9.hey.domain.car;

import lombok.Getter;

@Getter
public enum DefaultOptionCategory {
	POWER_TRAIN_PERFORMANCE("파워트레인/성능"),
	INTELLIGENT_SAFETY_TECHNOLOGY("지능형 안전 기술"),
	SAFETY_TECHNOLOGY("안전"),
	EXTERNAL_APPEARANCE("외관"),
	INTERNAL_APPEARANCE("내장"),
	SEAT("시트"),
	CONVENIENCE("편의"),
	MULTIMEDIA("멀티미디어");

	private final String name;

	DefaultOptionCategory(String name) {
		this.name = name;
	}
}
