package softeer.h9.hey.domain.car;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class InteriorColor {
    private int id;
    private String name;
    private String carImageUrl;
    private String colorImageUrl;
    private List<Tag> tags;
    private int trimId;
}
