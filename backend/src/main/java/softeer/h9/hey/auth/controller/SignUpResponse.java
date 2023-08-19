package softeer.h9.hey.auth.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpResponse {
	private final String refreshToken;
	private final String userName;
}
