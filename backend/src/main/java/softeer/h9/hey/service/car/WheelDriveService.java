package softeer.h9.hey.service.car;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.WheelDrive;
import softeer.h9.hey.dto.car.response.WheelDriveResponse;
import softeer.h9.hey.repository.car.WheelDriveRepository;

@Service
@RequiredArgsConstructor
public class WheelDriveService {

	private final WheelDriveRepository wheelDriveRepository;

	public WheelDriveResponse findWheelDrivesByModelId(final int modelId) {
		List<WheelDrive> wheelDrives = wheelDriveRepository.findAllByModelId(modelId);
		return WheelDriveResponse.of(wheelDrives);
	}

}
