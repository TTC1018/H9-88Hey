package softeer.h9.hey.auth.exception;

public class JoinException extends IllegalArgumentException {

	public static final String DUPLICATED_EMAIL_MESSAGE = "이미 사용중인 email 입니다.";

	public JoinException() {
		super(DUPLICATED_EMAIL_MESSAGE);
	}
}
