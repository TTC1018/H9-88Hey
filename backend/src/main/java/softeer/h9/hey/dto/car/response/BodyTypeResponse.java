package softeer.h9.hey.dto.car.response;

import lombok.Getter;
import softeer.h9.hey.domain.car.BodyType;

import java.util.List;

@Getter
public class BodyTypeResponse {
    private final List<BodyType> bodyTypes;

    private BodyTypeResponse(final List<BodyType> bodyTypes) {
        this.bodyTypes = bodyTypes;
    }

    public static BodyTypeResponse of(final List<BodyType> bodyTypes) {
        return new BodyTypeResponse(bodyTypes);
    }
}
