package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.BodyType;

@Getter
@RequiredArgsConstructor
public class BodyTypesResponse {

	private final List<BodyType> bodyTypes;

	public static BodyTypesResponse of(final List<BodyType> bodyTypes) {
		return new BodyTypesResponse(bodyTypes);
	}
}
