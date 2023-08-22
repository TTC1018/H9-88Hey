package softeer.h9.hey.exception.myChiving;

public class InValidAccessException extends IllegalArgumentException {
	public static final String INVALID_ACCESS_EXCEPTION = "유저가 저장하지 않은 마이카이빙 입니다.";

	public InValidAccessException() {
		super(INVALID_ACCESS_EXCEPTION);
	}
}
