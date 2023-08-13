package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;

@Getter
public class HGenuineAccessoriesResponse {

	private final List<HGenuineAccessoryResponse> hGenuineAccessoryResponses;

	private HGenuineAccessoriesResponse(final List<HGenuineAccessoryResponse> hGenuineAccessoryResponses) {
		this.hGenuineAccessoryResponses = hGenuineAccessoryResponses;
	}

	public static HGenuineAccessoriesResponse of(final List<HGenuineAccessoryResponse> hGenuineAccessoryResponses) {
		return new HGenuineAccessoriesResponse(hGenuineAccessoryResponses);
	}
}
