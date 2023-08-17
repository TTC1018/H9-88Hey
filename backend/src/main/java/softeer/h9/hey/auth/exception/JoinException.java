package softeer.h9.hey.auth.exception;

public class JoinException extends IllegalArgumentException {

	public static final String LOGIN_FAIL_MESSAGE = "로그인 실패";

	public JoinException() {
		super(LOGIN_FAIL_MESSAGE);
	}
}
