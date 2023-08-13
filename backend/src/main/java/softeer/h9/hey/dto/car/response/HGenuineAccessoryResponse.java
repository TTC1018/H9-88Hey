package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;

@Getter
public class HGenuineAccessoryResponse {

	private final boolean isAvailable;

	private final SelectOptionResponse selectOptionResponse;

	private HGenuineAccessoryResponse(final boolean isAvailable, final SelectOptionResponse selectOptionResponse) {
		this.isAvailable = isAvailable;
		this.selectOptionResponse = selectOptionResponse;
	}

	public static HGenuineAccessoryResponse of(final boolean isAvailable,
		final SelectOptionResponse selectOptionResponse) {
		return new HGenuineAccessoryResponse(isAvailable, selectOptionResponse);
	}
}
