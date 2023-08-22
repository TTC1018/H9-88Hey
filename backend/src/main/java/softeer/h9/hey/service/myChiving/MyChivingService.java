package softeer.h9.hey.service.myChiving;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
	public MyChivingIdResponse saveMyCar(int userId, final MyChivingSaveRequest myChivingSaveRequest) {

		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.SUBMIT.value );

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.from(myChivingId);
	}

	//임시 저장
	public MyChivingIdResponse temporarySaveMyCar(int userId, final MyChivingTempSaveRequest myChivingSaveRequest) {
		MyChivingSaveDto myChivingSaveDto = MyChivingSaveDto.from(myChivingSaveRequest);
		myChivingSaveDto.setUserId(userId);
		myChivingSaveDto.setSubmitted(IsSubmit.TEMPORARY_SUBMIT.value );

		Long myChivingId = myChivingRepository.saveMyCarToMyChiving(myChivingSaveDto);

		return MyChivingIdResponse.from(myChivingId);
	}

}
