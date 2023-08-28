package softeer.h9.hey.auth.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JoinRequest {
	@NotNull
	@Email
	private final String email;
	@Length(min = 8)
	private final String password;
	@NotEmpty
	private final String userName;
}
