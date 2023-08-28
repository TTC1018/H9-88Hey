package softeer.h9.hey.dto.archiving;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArchivingDto {
	private String feedId;
	private String modelName;
	private String creationDate;
	private boolean isPurchase;
	private String review;
	private Integer totalPrice;
	private List<String> tags;
	private TrimDto trim;
	private EngineDto engine;
	private BodyTypeDto bodyType;
	private WheelDriveDto wheelDrive;
	private InteriorColorDto interiorColor;
	private ExteriorColorDto exteriorColor;
	private Set<ArchivingSelectedOptionDto> selectedOptions;
}
