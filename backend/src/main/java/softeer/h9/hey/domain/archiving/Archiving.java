package softeer.h9.hey.domain.archiving;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Archiving {
	private Long feedId;
	private String modelName;
	private Boolean isPurchase;
	private LocalDate createdAt;
	private String review;
	private String trimName;
	private String engineName;
	private String bodyTypeName;
	private String wheelDriveName;
	private String exteriorColorName;
	private String interiorColorName;
	private String selectOptionId;
	private String selectOptionName;
}
