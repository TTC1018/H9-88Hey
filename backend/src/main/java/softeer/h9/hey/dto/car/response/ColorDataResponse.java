package softeer.h9.hey.dto.car.response;

import lombok.Getter;
import softeer.h9.hey.domain.car.ExteriorColor;
import softeer.h9.hey.domain.car.InteriorColor;

import java.util.List;

@Getter
public class ColorDataResponse {

    private final List<ExteriorColor> exteriorColors;
    private final List<InteriorColor> interiorColors;

    private ColorDataResponse(final List<ExteriorColor> exteriorColors, final List<InteriorColor> interiorColors) {
        this.exteriorColors = exteriorColors;
        this.interiorColors = interiorColors;
    }

    public static ColorDataResponse of(final List<ExteriorColor> exteriorColors, final List<InteriorColor> interiorColors) {
        return new ColorDataResponse(exteriorColors, interiorColors);
    }
}
