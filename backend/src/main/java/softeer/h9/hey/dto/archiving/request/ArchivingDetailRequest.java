package softeer.h9.hey.dto.archiving.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivingDetailRequest {

	@NotNull(message = "아카이빙 id는 필수 입력입니다.")
	@Min(value = 1, message = "올바르지 않은 아카이빙 아이디입니다. (아카이빙 id는 1 이상입니다.)")
	private Long id;

	private ArchivingDetailRequest(Long id) {
		this.id = id;
	}

	public static ArchivingDetailRequest of(Long id) {
		return new ArchivingDetailRequest(id);
	}
}
