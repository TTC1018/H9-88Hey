package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;

@Getter
public class HGenuineAccessoriesResponse {

	private final List<HGenuineAccessoryResponse> selectOptions;

	private HGenuineAccessoriesResponse(final List<HGenuineAccessoryResponse> selectOptions) {
		this.selectOptions = selectOptions;
	}

	public static HGenuineAccessoriesResponse of(final List<HGenuineAccessoryResponse> hGenuineAccessoryResponses) {
		return new HGenuineAccessoriesResponse(hGenuineAccessoryResponses);
	}
}
