package softeer.h9.hey.domain.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarInfo {
    private String carCode;
    private int trimId;
    private int engineId;
    private int bodyTypeId;
    private int wheelDriveId;
}
