package softeer.h9.hey.dto.archiving.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
	@Min(value = 1)
	private Integer limit = 4;
	@NotNull
	@Min(value = 1)
	private Integer offset;
}
