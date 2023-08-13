package softeer.h9.hey.dto.car.response;

import lombok.Getter;

@Getter
public class HGenuineAccessoryResponse {

	private final Boolean isAvailable;

	private final SelectOptionResponse selectOption;

	private HGenuineAccessoryResponse(final boolean isAvailable, final SelectOptionResponse selectOption) {
		this.isAvailable = isAvailable;
		this.selectOption = selectOption;
	}

	public static HGenuineAccessoryResponse of(final boolean isAvailable,
		final SelectOptionResponse selectOptionResponse) {
		return new HGenuineAccessoryResponse(isAvailable, selectOptionResponse);
	}
}
