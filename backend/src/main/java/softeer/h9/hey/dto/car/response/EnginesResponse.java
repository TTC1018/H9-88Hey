package softeer.h9.hey.dto.car.response;

import lombok.Getter;
import softeer.h9.hey.domain.car.Engine;

import java.util.List;

@Getter
public class EnginesResponse {
    private final List<Engine> engines;

    private EnginesResponse(List<Engine> engines) {
        this.engines = engines;
    }

    public static EnginesResponse of(List<Engine> engines) {
        return new EnginesResponse(engines);
    }
}