package softeer.h9.hey.auth.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
	@NotEmpty
	@Email
	private final String email;
	@NotEmpty
	private final String password;
}
