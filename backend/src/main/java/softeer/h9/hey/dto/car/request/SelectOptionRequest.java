package softeer.h9.hey.dto.car.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class SelectOptionRequest {
	@NotEmpty
	private final String carCode;

	private final List<String> selectOptions;

	private SelectOptionRequest(String car_code, List<String> select_option) {
		this.carCode = car_code;
		this.selectOptions = select_option;
	}

	public static final SelectOptionRequest of(String carCode, List<String> selectOptions) {
		return new SelectOptionRequest(carCode, selectOptions);
	}
}
