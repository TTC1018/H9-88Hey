package softeer.h9.hey.dto.car.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class SelectOptionRequest {
	@NotEmpty
	private String carCode;

	private List<String> selectOptions;

	public SelectOptionRequest(String car_code, List<String> select_option) {
		this.carCode = car_code;
		this.selectOptions = select_option;
	}
}
