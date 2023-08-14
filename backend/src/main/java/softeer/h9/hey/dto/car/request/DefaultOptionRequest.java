package softeer.h9.hey.dto.car.request;

import lombok.Getter;

@Getter
public class DefaultOptionRequest {
	private final String carCode;

	private DefaultOptionRequest(String car_code) {
		this.carCode = car_code;
	}

	public static DefaultOptionRequest of(String carCode) {
		return new DefaultOptionRequest(carCode);
	}
}
