package softeer.h9.hey.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
	private final String userId;
	private final String password;
}
