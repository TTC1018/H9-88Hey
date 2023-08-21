package softeer.h9.hey.auth.exception;

public class LoginException extends IllegalArgumentException {

	public static final String LOGIN_FAIL_MESSAGE = "로그인 실패";

	public LoginException() {
		super(LOGIN_FAIL_MESSAGE);
	}
}
