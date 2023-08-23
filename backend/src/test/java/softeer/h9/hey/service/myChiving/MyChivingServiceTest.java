package softeer.h9.hey.service.myChiving;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import softeer.h9.hey.dto.myChiving.MyChivingDto;
import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionDto;
import softeer.h9.hey.dto.myChiving.MyChivingSelectOptionFetchDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
import softeer.h9.hey.dto.myChiving.response.MyChivingResponse;
import softeer.h9.hey.dto.myChiving.response.MyChivingsResponse;
import softeer.h9.hey.repository.myChiving.MyChivingRepository;

class MyChivingServiceTest {

	MyChivingRepository myChivingRepository = Mockito.mock(MyChivingRepository.class);

	MyChivingService myChivingService = new MyChivingService(myChivingRepository);

	@Test
	@DisplayName("임시저장이나 마이카이빙 id를 반환해야 한다.")
	void tempSaveTest() {
		Long id = 123456789012345678L;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId,
			engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(false);

		when(myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto)).thenReturn(123456789012345678L);

		MyChivingIdResponse myChivingIdResponse = myChivingService.temporarySaveMyCar(1, myChivingTempSaveRequest);
		assertThat(myChivingIdResponse.getMyChivingId()).isEqualTo(Long.toString(id));
	}

	@Test
	@DisplayName("최종저장시 마이카이빙 id를 반환해야 한다.")
	void saveTest() {
		Long id = 123456789012345678L;
		Integer engineId = 1;
		Integer trimId = 1;
		Integer bodyTypeId = 1;
		Integer wheelTypeId = 1;
		Integer exteriorColorId = 1;
		Integer interiorColorId = 1;
		List<String> selectOptionIdList = List.of("TRP", "DUP", "VI2");

		MyChivingSaveRequest myChivingSaveRequest = new MyChivingSaveRequest(id, bodyTypeId, wheelTypeId, engineId,
			trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(true);

		when(myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto)).thenReturn(123456789012345678L);

		MyChivingIdResponse myChivingIdResponse = myChivingService.saveMyCar(1, myChivingSaveRequest);
		assertThat(myChivingIdResponse.getMyChivingId()).isEqualTo(Long.toString(id));
	}

	@Test
	@DisplayName("MyChivingRepository에서 반환하는 값을 토대로 Response 객체로 매핑하여 만들어야 한다.")
	void findMyChiving() throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		String dateString = "2023-08-19 15:30:00"; // Replace with your date-time string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

		List<MyChivingDto> myChivingDtoList = List.of(MyChivingDto.builder()
			.myChivingId(1234L)
			.isSaved(false)
			.lastModifiedDate(dateTime)
			.build());

		when(myChivingRepository.findMyChivingsByUserIdLimitAndOffset(1, 2, 0)).thenReturn(myChivingDtoList);

		List<Long> idList = myChivingDtoList.stream().map(MyChivingDto::getMyChivingId).collect(Collectors.toList());

		List<MyChivingSelectOptionFetchDto> myChivingSelectOptionFetchDtoList =
			List.of(MyChivingSelectOptionFetchDto.builder()
				.selectOptionId("DUP")
				.selectOptionName("듀얼 와이드 선루프")
				.additionalPrice(1000)
				.imageUrl("http://")
				.myChivingId(1234L)
				.subOptionName("듀얼 와이드 선루프")
				.build());

		when(myChivingRepository.findOptionDataByMyChivingIdList(idList)).thenReturn(myChivingSelectOptionFetchDtoList);

		Constructor<MyChivingRequest> declaredConstructor = MyChivingRequest.class.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		MyChivingRequest myChivingRequest = declaredConstructor.newInstance();
		ReflectionTestUtils.setField(myChivingRequest, "offset", 1);
		ReflectionTestUtils.setField(myChivingRequest, "limit", 1);
		MyChivingsResponse myChivingsResponse = myChivingService.findMyChivings(1, myChivingRequest);
		declaredConstructor.setAccessible(false);

		assertThat(myChivingsResponse.getMyChivings()).hasSize(1);

		MyChivingResponse myChivingResponse = myChivingsResponse.getMyChivings().get(0);

		assertThat(myChivingResponse.getMyChivingId()).isEqualTo(Long.toString(1234L));
		assertThat(myChivingResponse.getIsSaved()).isEqualTo(false);
		assertThat(myChivingResponse.getLastModifiedDate()).isEqualTo(dateTime);
		assertThat(myChivingResponse.getSelectOptions()).hasSize(1);
		MyChivingSelectOptionDto myChivingSelectOptionDto = myChivingResponse.getSelectOptions().get(0);
		assertThat(myChivingSelectOptionDto.getId()).isEqualTo("DUP");
		assertThat(myChivingSelectOptionDto.getName()).isEqualTo("듀얼 와이드 선루프");
		assertThat(myChivingSelectOptionDto.getAdditionalPrice()).isEqualTo(1000);
		assertThat(myChivingSelectOptionDto.getSubOptions()).contains("듀얼 와이드 선루프");
	}
}
