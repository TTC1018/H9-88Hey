package softeer.h9.hey.dto.archiving;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.domain.archiving.Tags;

@Getter
@Setter
@NoArgsConstructor
public class ArchivingDto {
	private Long feedId;
	private String modelName;
	private String creationDate;
	private boolean isPurchase;
	private String review;
	private Integer totalPrice;
	private Tags tags;
	private TrimDto trim;
	private EngineDto engine;
	private BodyTypeDto bodyType;
	private WheelDriveDto wheelDrive;
	private InteriorColorDto interiorColor;
	private ExteriorColorDto exteriorColor;
	private List<ArchivingSelectedOptionDto> selectedOptions;
}
