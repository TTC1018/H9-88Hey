package softeer.h9.hey.domain.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor()
public class BodyType {
    private int id;
    private String name;
    private String imageUrl;
    private int additionalPrice;
    private String description;
}
