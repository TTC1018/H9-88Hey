package softeer.h9.hey.dto.car.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagBySelectOptionRequest {
	@NotNull(message = "조회 할 id를 입력해주세요.")
	private String id;
	@Min(value = 0)
	private Integer limit;

	private TagBySelectOptionRequest(final String id, final Integer limit) {
		this.id = id;
		this.limit = limit;
	}
}
