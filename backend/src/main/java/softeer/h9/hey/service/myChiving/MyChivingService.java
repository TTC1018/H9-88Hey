package softeer.h9.hey.service.myChiving;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.myChiving.MyChiving;
import softeer.h9.hey.dto.myChiving.MyChivingSaveDto;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;
import softeer.h9.hey.dto.myChiving.response.MyChivingIdResponse;
import softeer.h9.hey.repository.myChiving.MyChivingRepository;

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


		//TODO 인증정보를 통해 사용자 식별 및 user_id 추출
		int userId = 1;
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.fromMyChivingSaveRequest(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.SUBMIT.value );

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.of(myChivingId);
	}

	//임시 저장
	public MyChivingIdResponse temporarySaveMyCar(final MyChivingTempSaveRequest myChivingSaveRequest) {
		//TODO 인증정보를 통해 사용자 식별 및 user_id 추출
		int userId = 1;
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.fromMyChivingTempSaveRequest(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.TEMPORARY_SUBMIT.value );

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.of(myChivingId);
	}

}
