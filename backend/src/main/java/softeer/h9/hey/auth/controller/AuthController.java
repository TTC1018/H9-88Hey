package softeer.h9.hey.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.service.AuthService;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public GlobalResponse<TokenResponse> join(@RequestBody JoinRequest joinRequest) {
		TokenResponse tokenResponse = authService.join(joinRequest);
		return GlobalResponse.ok(tokenResponse);
	}
}
