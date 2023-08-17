package softeer.h9.hey.service.car;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.car.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.car.response.MyChivingIdResponse;
import softeer.h9.hey.repository.car.MyChivingRepository;

@Service
@RequiredArgsConstructor
public class MyChivingService {

	@Getter
	private enum IsSubmit {

		SUBMIT(true),
		TEMPORARY_SUBMIT(false);

		private final boolean value;

		IsSubmit(boolean value) { this.value = value; }
	}

	private final MyChivingRepository myChivingRepository;

	//최종저장
	public MyChivingIdResponse saveMyCar(final MyChivingSaveRequest myChivingSaveRequest) {
		Long id = myChivingSaveRequest.getId();
		Integer engineId = myChivingSaveRequest.getEngineId();
		Integer trimId = myChivingSaveRequest.getTrimId();
		Integer bodyTypeId = myChivingSaveRequest.getBodyTypeId();
		Integer wheelTypeId = myChivingSaveRequest.getWheelTypeId();
		Integer exteriorColorId = myChivingSaveRequest.getExteriorColorId();
		Integer interiorColorId = myChivingSaveRequest.getInteriorColorId();
		List<String> selectOptionIdList = myChivingSaveRequest.getSelectOptionIdList();

		//TODO 인증정보를 통해 사용자 식별 및 user_id 추출
		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(id, engineId, trimId, bodyTypeId, wheelTypeId, exteriorColorId,
			interiorColorId, selectOptionIdList, IsSubmit.SUBMIT.value);

		return MyChivingIdResponse.of(myChivingId);
	}

	//임시 저장
	public MyChivingIdResponse temporarySaveMyCar(final MyChivingTempSaveRequest myChivingSaveRequest) {
		Long id = myChivingSaveRequest.getId();
		Integer engineId = myChivingSaveRequest.getEngineId();
		Integer trimId = myChivingSaveRequest.getTrimId();
		Integer bodyTypeId = myChivingSaveRequest.getBodyTypeId();
		Integer wheelTypeId = myChivingSaveRequest.getWheelTypeId();
		Integer exteriorColorId = myChivingSaveRequest.getExteriorColorId();
		Integer interiorColorId = myChivingSaveRequest.getInteriorColorId();
		List<String> selectOptionIdList = myChivingSaveRequest.getSelectOptionIdList();

		//TODO 인증정보를 통해 사용자 식별 및 user_id 추출
		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(id, engineId, trimId, bodyTypeId, wheelTypeId, exteriorColorId,
			interiorColorId, selectOptionIdList, IsSubmit.TEMPORARY_SUBMIT.value);

		return MyChivingIdResponse.of(myChivingId);
	}

}
