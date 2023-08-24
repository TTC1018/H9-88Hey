package softeer.h9.hey.dto.archiving.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.archiving.BodyTypeDto;
import softeer.h9.hey.dto.archiving.EngineDto;
import softeer.h9.hey.dto.archiving.ExteriorColorDto;
import softeer.h9.hey.dto.archiving.InteriorColorDto;
import softeer.h9.hey.dto.archiving.SelectOptionDto;
import softeer.h9.hey.dto.archiving.TrimDto;
import softeer.h9.hey.dto.archiving.WheelDriveDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ArchivingDetailResponse {
	private String feedId;
	private String modelName;
	private String creationDate;
	private boolean isPurchase;
	private String review;
	private Integer totalPrice;
	private TrimDto trim;
	private EngineDto engine;
	private BodyTypeDto bodyType;
	private WheelDriveDto wheelDrive;
	private InteriorColorDto interiorColor;
	private ExteriorColorDto exteriorColor;
	private List<String> tags = new ArrayList<>();
	private List<SelectOptionDto> selectedOptions = new ArrayList<>();
}
