package softeer.h9.hey.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JoinRequest {
	private final String email;
	private final String password;
	private final String userName;
}
