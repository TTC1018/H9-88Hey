package softeer.h9.hey.dto.car.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;

public class myChivingRequest {
	private final int id;
	@NotEmpty
	private final String carCode;
	@NotEmpty
	private final int exteriorColorId;
	@NotEmpty
	private final int interiorColorId;
	private final List<String> selectOptionIdList;

	public myChivingRequest(int id, String carCode, int exteriorColor, int interiorColor,
		List<String> selectOptions) {
		this.id = id;
		this.carCode = carCode;
		this.exteriorColorId = exteriorColor;
		this.interiorColorId = interiorColor;
		this.selectOptionIdList = selectOptions;
	}
}
