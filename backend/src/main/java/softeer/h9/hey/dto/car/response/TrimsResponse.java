package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;
import softeer.h9.hey.domain.car.Trim;

@Getter
public class TrimsResponse {
	private final List<Trim> trims;

	private TrimsResponse(List<Trim> trims) {
		this.trims = trims;
	}

	public static TrimsResponse of(List<Trim> trims) {
		return new TrimsResponse(trims);
	}
}
