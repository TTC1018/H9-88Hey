package softeer.h9.hey.dto.car.request;

import java.util.List;

import lombok.Getter;

@Getter
public class SelectOptionRequest {

	private final String carCode;
	private final Integer modelId;

	private final List<String> selectOptions;

	public SelectOptionRequest(final String car_code, final Integer model_id, final List<String> select_option) {
		this.carCode = car_code;
		this.modelId = model_id;
		this.selectOptions = select_option;
	}
}
