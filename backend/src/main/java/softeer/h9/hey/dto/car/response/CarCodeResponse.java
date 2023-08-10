package softeer.h9.hey.dto.car.response;

import lombok.Getter;

@Getter
public class CarCodeResponse {
    private final String carCode;

    private CarCodeResponse(String carCode) {
        this.carCode = carCode;
    }

    public static CarCodeResponse of(String carCode) {

        return new CarCodeResponse(carCode);
    }
}
