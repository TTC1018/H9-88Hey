package softeer.h9.hey.dto.car.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyChivingRequest {
	@JsonProperty("id")
	private final Integer id;
	@JsonProperty("carCode")
	@NotNull
	private final String carCode;
	@JsonProperty("exteriorColor")
	@NotNull
	private final Integer exteriorColorId;
	@JsonProperty("interiorColor")
	@NotNull
	private final Integer interiorColorId;
	@JsonProperty("selectOptions")
	private final List<String> selectOptionIdList;

}
