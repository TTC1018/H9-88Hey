package softeer.h9.hey.service.car;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.DefaultOption;
import softeer.h9.hey.dto.car.DefaultOptionDto;
import softeer.h9.hey.dto.car.DefaultSubOptionDto;
import softeer.h9.hey.dto.car.request.DefaultOptionRequest;
import softeer.h9.hey.dto.car.response.DefaultOptionsResponse;
import softeer.h9.hey.repository.car.DefaultOptionRepository;

@DisplayName("DefaultOptionService 테스트")
class DefaultOptionServiceTest {
	private final DefaultOptionRepository defaultOptionRepository = Mockito.mock(DefaultOptionRepository.class);
	private final DefaultOptionService defaultOptionService = new DefaultOptionService(defaultOptionRepository);

	@Test
	@DisplayName("carCode에 해당하는 기본 옵션을 조회한다.")
	void findAllByCarCode() {
		String carCode = "LXJJ8MST5";
		DefaultOptionRequest request = DefaultOptionRequest.of("LXJJ8MST5");

		DefaultOption defaultOption1 = new DefaultOption("id1", "name1", "description1", "imageUrl1", "category1");
		DefaultOption defaultOption2 = new DefaultOption("id2", "name1", "description2", "imageUrl2", "category1");
		DefaultOption defaultOption3 = new DefaultOption("id3", "name1", "description3", "imageUrl3", "category3");

		when(defaultOptionRepository.findAllDefaultSubOptionByCarCode(carCode))
			.thenReturn(List.of(defaultOption1, defaultOption2, defaultOption3));

		DefaultOptionsResponse response = defaultOptionService.findAllByCarCode(request);
		List<DefaultOptionDto> defaultOptionDtoList = response.getDefaultOptions();

		assertEquals(2, defaultOptionDtoList.size());

		List<DefaultSubOptionDto> defaultSubOptionDtoList1 = defaultOptionDtoList.get(0).getSubOptions();
		List<DefaultSubOptionDto> defaultSubOptionDtoList2 = defaultOptionDtoList.get(1).getSubOptions();

		assertEquals(1, defaultSubOptionDtoList1.size());
		assertEquals(2, defaultSubOptionDtoList2.size());
	}
}
