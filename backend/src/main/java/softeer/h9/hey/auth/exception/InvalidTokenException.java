package softeer.h9.hey.auth.exception;

public class InvalidTokenException extends IllegalArgumentException {

	public static final String INVALID_TOKEN_EXCEPTION = "유효하지 않은 토큰입니다.";

	public InvalidTokenException() {
		super(INVALID_TOKEN_EXCEPTION);
	}
}
