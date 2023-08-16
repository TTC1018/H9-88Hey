package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;
import softeer.h9.hey.dto.car.SelectOptionByModelDto;

@Getter
public class SelectOptionByModelIdResponse {
	private final List<SelectOptionByModelDto> SelectOptions;

	private SelectOptionByModelIdResponse(List<SelectOptionByModelDto> selectOptions) {
		SelectOptions = selectOptions;
	}

	public static SelectOptionByModelIdResponse from(List<SelectOptionByModelDto> selectOptions) {
		return new SelectOptionByModelIdResponse(selectOptions);
	}
}
