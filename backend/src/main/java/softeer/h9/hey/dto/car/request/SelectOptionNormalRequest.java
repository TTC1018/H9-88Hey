package softeer.h9.hey.dto.car.request;

import lombok.Getter;

@Getter
public class SelectOptionNormalRequest {
	private final Integer modelId;

	private SelectOptionNormalRequest(Integer model_id) {
		this.modelId = model_id;
	}

	public static SelectOptionNormalRequest from(Integer modelId) {
		return new SelectOptionNormalRequest(modelId);
	}
}
