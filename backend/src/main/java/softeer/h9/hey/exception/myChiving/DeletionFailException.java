package softeer.h9.hey.exception.myChiving;

public class DeletionFailException extends RuntimeException {
	private static final String DELETION_FAIL_EXCEPTION = "삭제시 에러가 발생하였습니다.";

	public DeletionFailException() {
		super(DELETION_FAIL_EXCEPTION);
	}
}
