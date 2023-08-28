package softeer.h9.hey.dto.car.response;

import java.util.List;

import lombok.Getter;
import softeer.h9.hey.domain.car.WheelDrive;

@Getter
public class WheelDriveResponse {

	private final List<WheelDrive> wheelDrives;

	private WheelDriveResponse(List<WheelDrive> wheelDrives) {
		this.wheelDrives = wheelDrives;
	}

	public static WheelDriveResponse of(List<WheelDrive> wheelDrives) {
		return new WheelDriveResponse(wheelDrives);
	}
}
