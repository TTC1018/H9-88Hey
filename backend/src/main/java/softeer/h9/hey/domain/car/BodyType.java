package softeer.h9.hey.domain.car;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyType {
    private int id;
    private String name;
    private String imageUrl;
    private int additionalPrice;
    private String description;
}
