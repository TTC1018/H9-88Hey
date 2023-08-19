package softeer.h9.hey.domain.myChiving;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.h9.hey.dto.myChiving.request.MyChivingSaveRequest;
import softeer.h9.hey.dto.myChiving.request.MyChivingTempSaveRequest;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyChiving {
	private Long id;
	private int userId;
	private int interiorColorId;
	private int exteriorColorId;
	private int bodyTypeId;
	private int wheelTypeId;
	private int trimId;
	private int engineId;
	private boolean isSubmitted;
	private LocalDateTime lastModifiedDate;
}
