package softeer.h9.hey.dto.myChiving.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.h9.hey.dto.archiving.BodyTypeDto;
import softeer.h9.hey.dto.archiving.EngineDto;
import softeer.h9.hey.dto.archiving.ExteriorColorDto;
import softeer.h9.hey.dto.archiving.InteriorColorDto;
import softeer.h9.hey.dto.archiving.TrimDto;
import softeer.h9.hey.dto.archiving.WheelDriveDto;
import softeer.h9.hey.dto.myChiving.ModelDto;
import softeer.h9.hey.dto.myChiving.MyChivingDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MyChivingResponse {
	private String myChivingId;
	private LocalDateTime lastModifiedDate;
	private Boolean isSaved;
	private ModelDto model;
	private TrimDto trim;
	private EngineDto engine;
	private BodyTypeDto bodyType;
	private WheelDriveDto wheelDrive;
	private InteriorColorDto interiorColor;
	private ExteriorColorDto exteriorColor;
	private List<MyChivingSelectOptionDto> selectOptions;

	public static MyChivingResponse of(MyChivingDto myChivingDto,
		List<MyChivingSelectOptionDto> myChivingSelectOptionDtoList) {
		return MyChivingResponse.builder()
			.myChivingId(Long.toString(myChivingDto.getMyChivingId()))
			.lastModifiedDate(myChivingDto.getLastModifiedDate())
			.isSaved(myChivingDto.getIsSaved())
			.model(myChivingDto.getModelDto())
			.trim(myChivingDto.getTrim())
			.engine(myChivingDto.getEngine())
			.bodyType(myChivingDto.getBodyType())
			.wheelDrive(myChivingDto.getWheelDrive())
			.interiorColor(myChivingDto.getInteriorColor())
			.exteriorColor(myChivingDto.getExteriorColor())
			.selectOptions(myChivingSelectOptionDtoList)
			.build();
	}

}
