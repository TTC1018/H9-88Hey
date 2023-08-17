package softeer.h9.hey.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenResponse {
	private final String accessToken;
	private final String refreshToken;
}
