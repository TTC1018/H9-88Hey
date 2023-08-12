package softeer.h9.hey.service.car;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.ExteriorColor;
import softeer.h9.hey.domain.car.InteriorColor;
import softeer.h9.hey.dto.car.response.ColorDataResponse;
import softeer.h9.hey.repository.car.AvailableColorRepository;
import softeer.h9.hey.repository.car.ExteriorColorRepository;
import softeer.h9.hey.repository.car.InteriorColorRepository;

@Service
@RequiredArgsConstructor
public class ColorDataService {

	private final ExteriorColorRepository exteriorColorRepository;
	private final InteriorColorRepository interiorColorRepository;
	private final AvailableColorRepository availableColorRepository;

	public ColorDataResponse findAvailableColorsByTrimId(final int trimId) {
		List<ExteriorColor> exteriorColors = exteriorColorRepository.findAllByTrimId(trimId);
		List<InteriorColor> interiorColors = interiorColorRepository.findAllByTrimId(trimId);

		// 트림과 외장 색상에 가능한 내장 색상 조합.
		for (ExteriorColor exteriorColor : exteriorColors) {
			int exteriorColorId = exteriorColor.getId();
			exteriorColor.setAvailableInteriorColors(
				availableColorRepository.findAllByTrimIdAndExteriorColor(trimId, exteriorColorId));
		}

		return ColorDataResponse.of(exteriorColors, interiorColors);
	}
}
