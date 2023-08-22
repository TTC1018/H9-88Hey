package softeer.h9.hey.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenRequest {
	private final String refreshToken;
}
