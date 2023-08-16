package softeer.h9.hey.dto.car.request;

import java.util.List;

import lombok.Getter;

@Getter
public class SelectOptionRequest {

	private final String carCode;
	private final String modelId;

	private final List<String> selectOptions;

	public SelectOptionRequest(final String car_code, final String model_Id, final List<String> select_option) {
		this.carCode = car_code;
		this.modelId = model_Id;
		this.selectOptions = select_option;
	}
}
