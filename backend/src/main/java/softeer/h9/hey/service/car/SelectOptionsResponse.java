package softeer.h9.hey.service.car;

import java.util.List;

import lombok.Getter;

@Getter
public class SelectOptionsResponse {
	private final List<SelectOptionResponse> selectOptions;

	private SelectOptionsResponse(List<SelectOptionResponse> selectOptions) {
		this.selectOptions = selectOptions;
	}

	public static SelectOptionsResponse from(List<SelectOptionResponse> selectOptionResponses) {
		return new SelectOptionsResponse(selectOptionResponses);
	}
}
