package softeer.h9.hey.service.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.domain.car.SelectOption;
import softeer.h9.hey.dto.car.DisabledOptionIdDto;
import softeer.h9.hey.dto.car.SelectOptionByModelDto;
import softeer.h9.hey.dto.car.request.SelectOptionNormalRequest;
import softeer.h9.hey.dto.car.request.SelectOptionRequest;
import softeer.h9.hey.dto.car.response.HGenuineAccessoriesResponse;
import softeer.h9.hey.dto.car.response.HGenuineAccessoryResponse;
import softeer.h9.hey.dto.car.response.SelectOptionByModelIdResponse;
import softeer.h9.hey.dto.car.response.SelectOptionResponse;
import softeer.h9.hey.dto.car.response.SelectOptionsResponse;
import softeer.h9.hey.repository.car.SelectOptionRepository;

@DisplayName("선택 옵션 조회 테스트")
class SelectOptionServiceTest {
	private final SelectOptionRepository selectOptionRepository = Mockito.mock(SelectOptionRepository.class);
	private final SelectOptionService selectOptionService = new SelectOptionService(selectOptionRepository);

	@Test
	@DisplayName("model id 에 따른 선택 옵션들을 조회한다.")
	void findAllSelectOptionByModelId() {
		SelectOptionNormalRequest selectOptionNormalRequest = SelectOptionNormalRequest.from(1);

		SelectOptionByModelDto selectOptionByModelDto = Mockito.mock(SelectOptionByModelDto.class);
		when(selectOptionRepository.findAllSelectOptionByModelId(any()))
			.thenReturn(List.of(selectOptionByModelDto, selectOptionByModelDto, selectOptionByModelDto));

		SelectOptionByModelIdResponse result = selectOptionService.findAllSelectOptionByModelId(
			selectOptionNormalRequest);
		List<SelectOptionByModelDto> resultSelectOptions = result.getSelectOptions();

		assertThat(resultSelectOptions).hasSize(3);
	}

	@Test
	@DisplayName("carCode에 해당하는 차량에 적용할 수 있는 선택 옵션 목록을 조회한다.")
	void findSelectOptionTest() {
		SelectOptionRequest selectOptionRequest = SelectOptionRequest.of("LXJJ8MST5", null);
		when(selectOptionRepository.findSelectOptionsByCarCode(any()))
			.thenReturn(List.of(Mockito.mock(SelectOption.class), Mockito.mock(SelectOption.class)));

		SelectOptionsResponse selectOptionResponses = selectOptionService.findSelectOption(selectOptionRequest);
		List<SelectOptionResponse> selectOptions = selectOptionResponses.getSelectOptions();

		assertThat(selectOptions).hasSize(2);
	}

	@Test
	@DisplayName("carCode에 해당하는 차량에 적용할 수 있는 N Performance 옵션 목록을 조회한다.")
	void findNPerformanceOptionTest() {
		SelectOptionRequest selectOptionRequest = SelectOptionRequest.of("LXJJ8MST5", null);
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
		SelectOptionRequest selectOptionRequest = SelectOptionRequest.of("LXJJ7DCT5", selectOptions);

		when(selectOptionRepository.findHGenuineAccessoriesByCarCode(any()))
			.thenReturn(List.of(
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class),
				Mockito.mock(SelectOption.class)));

		when(selectOptionRepository.findDisabledOptionIdsBySelectOptionIds(any()))
			.thenReturn(List.of(
				DisabledOptionIdDto.of("test1234"),
				DisabledOptionIdDto.of("test1234"),
				DisabledOptionIdDto.of("test1234"),
				DisabledOptionIdDto.of("test1234")));

		HGenuineAccessoriesResponse hGenuineAccessoriesResponse = selectOptionService.findHGenuineOption(
			selectOptionRequest);
		List<HGenuineAccessoryResponse> hGenuineAccessoryResponses = hGenuineAccessoriesResponse.getSelectOptions();

		assertThat(hGenuineAccessoryResponses).hasSize(7);

		for (HGenuineAccessoryResponse hGenuineAccessoryResponse : hGenuineAccessoryResponses) {
			assertThat(hGenuineAccessoryResponse.getIsAvailable()).isNotNull();
		}
	}
}
