package softeer.h9.hey.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidatedUserRequest {
	private final String token;
}
