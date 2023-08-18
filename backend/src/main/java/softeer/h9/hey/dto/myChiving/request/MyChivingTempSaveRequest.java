package softeer.h9.hey.dto.myChiving.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyChivingTempSaveRequest {
	@JsonProperty("id")
	private final Long id;
	@JsonProperty("bodyType")
	private final Integer bodyTypeId;
	@JsonProperty("wheelType")
	private final Integer wheelTypeId;
	@JsonProperty("engine")
	private final Integer engineId;
	@JsonProperty("trim")
	private final Integer trimId;
	@JsonProperty("exteriorColor")
	private final Integer exteriorColorId;
	@JsonProperty("interiorColor")
	private final Integer interiorColorId;
	@JsonProperty("selectOptions")
	private final List<String> selectOptionIdList;

}
