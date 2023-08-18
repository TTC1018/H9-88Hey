package softeer.h9.hey.service.myChiving;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
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

		MyChivingTempSaveRequest myChivingTempSaveRequest = new MyChivingTempSaveRequest(id, bodyTypeId, wheelTypeId, engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.fromMyChivingTempSaveRequest(myChivingTempSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(false);

		System.out.println(myChivingSaveDto.getId());

		when(myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto)).thenReturn(123456789012345678L);

		MyChivingIdResponse myChivingIdResponse = myChivingService.temporarySaveMyCar(myChivingTempSaveRequest);
		assertThat(myChivingIdResponse.getMyChivingId()).isEqualTo(id);
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

		MyChivingSaveRequest myChivingSaveRequest = new MyChivingSaveRequest(id, bodyTypeId, wheelTypeId, engineId, trimId, exteriorColorId, interiorColorId, selectOptionIdList);
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.fromMyChivingSaveRequest(myChivingSaveRequest);
		myChivingSaveDto.setUserId(1);
		myChivingSaveDto.setSubmitted(true);

		System.out.println(myChivingSaveDto.getId());

		when(myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto)).thenReturn(123456789012345678L);

		MyChivingIdResponse myChivingIdResponse = myChivingService.saveMyCar(myChivingSaveRequest);
		assertThat(myChivingIdResponse.getMyChivingId()).isEqualTo(id);
	}
}
