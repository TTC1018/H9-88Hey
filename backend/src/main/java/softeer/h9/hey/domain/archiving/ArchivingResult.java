package softeer.h9.hey.domain.archiving;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ArchivingResult {
	private Long feedId;
	private String modelName;
	private String review;
	private boolean isPurchase;
	private LocalDate creationDate;
	private Integer trimId;
	private String trimName;
	private Integer trimPrice;
	private Integer engineId;
	private String engineName;
	private Integer enginePrice;
	private Integer bodyTypeId;
	private String bodyTypeName;
	private Integer bodyTypePrice;
	private Integer wheelDriveId;
	private String wheelDriveName;
	private Integer wheelDrivePrice;
	private Integer exteriorColorId;
	private String exteriorColorName;
	private String exteriorColorCarImageUrl;
	private String exteriorColorColorImageUrl;
	private Integer exteriorColorAdditionalPrice;
	private Integer interiorColorId;
	private String interiorColorName;
	private String interiorColorColorImageUrl;
	private String selectOptionId;
	private String selectOptionName;
	private String selectOptionImageUrl;
	private String selectOptionCategory;
	private Integer selectOptionAdditionalPrice;
	private String selectOptionReview;
	private String subOptionName;
}
