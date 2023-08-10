package softeer.h9.hey.dto.car.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.BodyType;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BodyTypeResponse {

    private final List<BodyType> bodyTypes;

    public static BodyTypeResponse of(final List<BodyType> bodyTypes) {
        return new BodyTypeResponse(bodyTypes);
    }
}
