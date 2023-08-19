package softeer.h9.hey.dto.global.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GlobalResponse<T> {

	private int statusCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public static <T> GlobalResponse<T> ok(T body) {
		return GlobalResponse.<T>builder()
			.statusCode(HttpStatus.OK.value())
			.data(body)
			.build();
	}

	public static GlobalResponse<?> error(HttpStatus httpStatus, String message) {
		return GlobalResponse.builder()
			.statusCode(httpStatus.value())
			.message(message)
			.build();
	}
}
