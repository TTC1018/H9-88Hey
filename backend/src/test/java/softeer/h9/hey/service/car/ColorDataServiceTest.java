package softeer.h9.hey.service.car;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.ExteriorColor;
import softeer.h9.hey.domain.car.InteriorColor;
import softeer.h9.hey.dto.car.response.ColorDataResponse;
import softeer.h9.hey.repository.car.AvailableColorRepository;
import softeer.h9.hey.repository.car.ExteriorColorRepository;
import softeer.h9.hey.repository.car.InteriorColorRepository;

@DisplayName("ColorDataService 테스트")
public class ColorDataServiceTest {

	private final ExteriorColorRepository exteriorColorRepository = Mockito.mock(ExteriorColorRepository.class);
	private final InteriorColorRepository interiorColorRepository = Mockito.mock(InteriorColorRepository.class);
	private final AvailableColorRepository availableColorRepository = Mockito.mock(AvailableColorRepository.class);
	private final ColorDataService colorDataService = new ColorDataService(exteriorColorRepository,
		interiorColorRepository,
		availableColorRepository);

	@Test
	@DisplayName("trim id에 따른 가능한 색상들을 조회한다.")
	void findAvailableColorsByTrimId() {
		int trimId = 1;
		ExteriorColor exteriorColorMock = ExteriorColor.builder()
			.id(1)
			.name("어비스 블랙 펄")
			.carImagePath("https://car/image/avis")
			.colorImageUrl("https://color/image/avis")
			.build();
		InteriorColor interiorColorMock = InteriorColor.builder()
			.id(2)
			.name("레드")
			.carImageUrl("https://car/image/red")
			.colorImageUrl("https://color/image/red")
			.build();
		List<Integer> availableColors = List.of(1);
		when(exteriorColorRepository.findAllByTrimId(trimId)).thenReturn(List.of(exteriorColorMock));
		when(interiorColorRepository.findAllByTrimId(trimId)).thenReturn(List.of(interiorColorMock));
		when(availableColorRepository.findAllByTrimIdAndExteriorColor(trimId, exteriorColorMock.getId()))
			.thenReturn(availableColors);

		ColorDataResponse colorDataResponse = colorDataService.findAvailableColorsByTrimId(trimId);
		List<ExteriorColor> exteriorColors = colorDataResponse.getExteriorColors();
		List<InteriorColor> interiorColors = colorDataResponse.getInteriorColors();

		assertAll(
			() -> assertEquals(1, exteriorColors.size()),
			() -> assertEquals(1, interiorColors.size()),
			() -> assertTrue(exteriorColors.stream()
				.anyMatch(exteriorColor -> exteriorColor.getAvailableInteriorColors().stream()
					.anyMatch(id -> id.equals(1))
				)
			));
	}
}
