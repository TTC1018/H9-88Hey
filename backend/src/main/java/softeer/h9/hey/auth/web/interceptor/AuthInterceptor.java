package softeer.h9.hey.auth.web.interceptor;

import static org.springframework.http.HttpHeaders.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import softeer.h9.hey.auth.exception.InvalidTokenException;
import softeer.h9.hey.auth.service.JwtTokenProvider;
import softeer.h9.hey.dto.global.response.GlobalResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	public static final String BEARER = "Bearer ";
	public static final String USER_NAME = "userName";
	public static final String SUBJECT = "sub";
	public static final String USER_ID = "userId";
	public static final String APPLICATION_JSON = "application/json; charset=UTF-8";
	public static final String BAD_REQUEST_ERROR_MESSAGE = "잘못된 요청";

	private final JwtTokenProvider tokenProvider;
	private final ObjectMapper objectMapper;

	private final List<String> allowedMethod = List.of("OPTIONS");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (allowedMethod.contains(request.getMethod())) {
			return true;
		}
		try {
			String jwtToken = request.getHeader(AUTHORIZATION).substring(BEARER.length());
			Map<String, Object> claims = tokenProvider.getClaimsFromToken(jwtToken);
			request.setAttribute(USER_ID, claims.get(SUBJECT));
			request.setAttribute(USER_NAME, claims.get(USER_NAME));
			return true;
		} catch (InvalidTokenException e) {
			sendErrorResponse(response, HttpStatus.UNAUTHORIZED, InvalidTokenException.INVALID_TOKEN_EXCEPTION);
			return false;
		} catch (RuntimeException e) {
			sendErrorResponse(response, HttpStatus.BAD_REQUEST, BAD_REQUEST_ERROR_MESSAGE);
			return false;
		}
	}

	private void sendErrorResponse(HttpServletResponse response, HttpStatus httpStatus, String message)
		throws IOException {
		GlobalResponse<?> errorResponse = GlobalResponse.error(httpStatus, message);
		response.setStatus(httpStatus.value());
		response.setContentType(APPLICATION_JSON);
		try (PrintWriter writer = response.getWriter()) {
			writer.write(objectMapper.writeValueAsString(errorResponse));
		}
	}
}
