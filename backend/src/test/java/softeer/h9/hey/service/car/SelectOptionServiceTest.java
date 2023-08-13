package softeer.h9.hey.service.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.dto.car.SubOptionIdDto;
import softeer.h9.hey.dto.car.request.SelectOptionRequest;
import softeer.h9.hey.dto.car.response.HGenuineAccessoriesResponse;
import softeer.h9.hey.dto.car.response.HGenuineAccessoryResponse;
import softeer.h9.hey.dto.car.response.SelectOptionResponse;
import softeer.h9.hey.dto.car.response.SelectOptionsResponse;
import softeer.h9.hey.repository.car.SelectOptionRepository;

@DisplayName("선택 옵션 조회 테스트")
class SelectOptionServiceTest {
	private final SelectOptionRepository selectOptionRepository = Mockito.mock(SelectOptionRepository.class);
	private final SelectOptionService selectOptionService = new SelectOptionService(selectOptionRepository);

	@Test
	@DisplayName("carCode에 해당하는 차량에 적용할 수 있는 선택 옵션 목록을 조회한다.")
	void findSelectOptionTest() {
		SelectOptionRequest selectOptionRequest = new SelectOptionRequest("LXJJ8MST5", null);
		when(selectOptionRepository.findSelectOptionsByCarCode(any()))
			.thenReturn(List.of(Mockito.mock(SelectOption.class), Mockito.mock(SelectOption.class)));

		SelectOptionsResponse selectOptionResponses = selectOptionService.findSelectOption(selectOptionRequest);
		List<SelectOptionResponse> selectOptions = selectOptionResponses.getSelectOptions();

		assertThat(selectOptions).hasSize(2);
	}

	@Test
	@DisplayName("carCode에 해당하는 차량에 적용할 수 있는 N Performance 옵션 목록을 조회한다.")
	void findNPerformanceOptionTest() {
		SelectOptionRequest selectOptionRequest = new SelectOptionRequest("LXJJ8MST5", null);
		when(selectOptionRepository.findNPerformanceByCarCode(any()))
			.thenReturn(List.of(
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class)));

		SelectOptionsResponse selectOptionResponses = selectOptionService.findNPerformanceOption(selectOptionRequest);
		List<SelectOptionResponse> selectOptions = selectOptionResponses.getSelectOptions();

		assertThat(selectOptions).hasSize(3);
	}

	@Test
	@DisplayName("carCode에 해당하는 차량에 적용할 수 있는 H Genuine Accessory 옵션 목록을 조회하고 선택 옵션에 따른 선택 가능 여부를 반환한다.")
	void findHGenuineOptionTest() {
		List<String> selectOptions = List.of("VI2");
		SelectOptionRequest selectOptionRequest = new SelectOptionRequest("LXJJ7DCT5", selectOptions);

		when(selectOptionRepository.findHGenuineAccessoriesByCarCode(any()))
			.thenReturn(List.of(
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class)));

		when(selectOptionRepository.findSubOptionIdsBySelectOptionIds(any()))
			.thenReturn(List.of(
				Mockito.mock(SubOptionIdDto.class),
				Mockito.mock(SubOptionIdDto.class),
				Mockito.mock(SubOptionIdDto.class),
				Mockito.mock(SubOptionIdDto.class)));

		HGenuineAccessoriesResponse hGenuineAccessoriesResponse = selectOptionService.findHGenuineOption(
			selectOptionRequest);
		List<HGenuineAccessoryResponse> hGenuineAccessoryResponses = hGenuineAccessoriesResponse.getSelectOptions();

		assertThat(hGenuineAccessoryResponses).hasSize(7);

		for (HGenuineAccessoryResponse hGenuineAccessoryResponse : hGenuineAccessoryResponses) {
			assertThat(hGenuineAccessoryResponse.getIsAvailable()).isNotNull();
		}
	}
}
