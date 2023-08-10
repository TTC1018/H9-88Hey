package softeer.h9.hey.dto.car.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarCodeRequest {
    private final int trim_id;
    private final int engine_id;
    private final int body_type_id;
    private final int wheel_drive_id;

    public int getTrimId() {
        return trim_id;
    }

    public int getEngineId() {
        return engine_id;
    }

    public int getBodyTypeId() {
        return body_type_id;
    }

    public int getWheelDriveId() {
        return wheel_drive_id;
    }
}
