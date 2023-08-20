package softeer.h9.hey.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.dto.request.AccessTokenRequest;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.exception.InvalidTokenException;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.service.AuthService;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthController {

	public static final String BEARER = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
	private final AuthService authService;

	@PostMapping("/signup")
	public GlobalResponse<TokenResponse> join(@RequestBody JoinRequest joinRequest) {
		TokenResponse tokenResponse = authService.join(joinRequest);
		return GlobalResponse.ok(tokenResponse);
	}

	@PostMapping("/signin")
	public GlobalResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
		TokenResponse tokenResponse = authService.login(loginRequest);
		return GlobalResponse.ok(tokenResponse);
	}

	@PostMapping("/access-token")
	public GlobalResponse<TokenResponse> getAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader(AUTHORIZATION);
		if (authorization == null || !authorization.startsWith(BEARER)) {
			throw new IllegalStateException();
		}
		String refreshToken = authorization.substring(BEARER.length());
		AccessTokenRequest accessTokenRequest = new AccessTokenRequest(refreshToken);

		TokenResponse tokenResponse = authService.republishAccessToken(accessTokenRequest);
		return GlobalResponse.ok(tokenResponse);
	}

	@ExceptionHandler
	public ResponseEntity<?> handlerException(LoginException e) {
		GlobalResponse<?> errorResponse = GlobalResponse.error(HttpStatus.UNAUTHORIZED, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler
	public ResponseEntity<?> handlerException(JoinException e) {
		GlobalResponse<?> errorResponse = GlobalResponse.error(HttpStatus.CONFLICT, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<?> handlerException(InvalidTokenException e) {
		GlobalResponse<?> errorResponse = GlobalResponse.error(HttpStatus.UNAUTHORIZED, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
}
