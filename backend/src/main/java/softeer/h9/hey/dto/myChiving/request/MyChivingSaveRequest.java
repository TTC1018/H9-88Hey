package softeer.h9.hey.dto.myChiving.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyChivingSaveRequest {
	@JsonProperty("myChivingId")
	@NotNull
	private final Long id;
	@JsonProperty("bodyType")
	@NotNull
	private final Integer bodyTypeId;
	@JsonProperty("wheelType")
	@NotNull
	private final Integer wheelTypeId;
	@JsonProperty("engine")
	@NotNull
	private final Integer engineId;
	@JsonProperty("trim")
	@NotNull
	private final Integer trimId;
	@JsonProperty("exteriorColor")
	@NotNull
	private final Integer exteriorColorId;
	@JsonProperty("interiorColor")
	@NotNull
	private final Integer interiorColorId;
	@JsonProperty("selectOptions")
	private final List<String> selectOptionIdList;

}
