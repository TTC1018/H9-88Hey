package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;

@Getter
public class HGenuineAccessoryResponse {
	private final Boolean isAvailable;
	private final String id;
	private final String name;
	private final String imageUrl;
	private final int additionalPrice;
	private final List<SubOptionResponses> subOptions;

	private HGenuineAccessoryResponse(final boolean isAvailable, final SelectOptionResponse selectOption) {
		this.isAvailable = isAvailable;
		this.id = selectOption.getId();
		this.name = selectOption.getName();
		this.imageUrl = selectOption.getImageUrl();
		this.additionalPrice = selectOption.getAdditionalPrice();
		this.subOptions = selectOption.getSubOptions();
	}

	public static HGenuineAccessoryResponse of(final boolean isAvailable,
		final SelectOptionResponse selectOptionResponse) {
		return new HGenuineAccessoryResponse(isAvailable, selectOptionResponse);
	}
}
