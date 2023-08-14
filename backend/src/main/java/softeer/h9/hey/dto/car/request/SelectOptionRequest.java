package softeer.h9.hey.dto.car.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class SelectOptionRequest {
	@NotEmpty
	private String carCode;

	private List<String> selectOption;

	public SelectOptionRequest(String car_code, List<String> select_option) {
		this.carCode = car_code;
		this.selectOption = select_option;
	}
}
