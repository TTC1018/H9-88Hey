package softeer.h9.hey.exception;

import java.util.zip.DataFormatException;

public class DataNotFoundException extends DataFormatException {
	public static final String DATA_NOT_FOUND_MESSAGE = "요청한 데이터가 존재하지 않습니다.";

	public DataNotFoundException() {
		super(DATA_NOT_FOUND_MESSAGE);
	}

	public DataNotFoundException(String message) {
		super(message);
	}
}
