package softeer.h9.hey.domain.car;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExteriorColor {
    private int id;
    private String name;
    private String carImagePath;
    private String colorImageUrl;
    private List<Tag> tags;
    private List<Integer> availableInteriorColors;
    private int trimId;
}
