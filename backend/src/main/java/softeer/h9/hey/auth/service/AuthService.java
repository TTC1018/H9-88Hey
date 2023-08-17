package softeer.h9.hey.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;

	public TokenResponse join(JoinRequest joinRequest) {
		User user = mapToUser(joinRequest);
		validateUniqueUserId(user.getUserId());

		user = userRepository.save(user);
		String userPk = String.valueOf(user.getId());

		String accessToken = jwtTokenProvider.generateAccessToken(userPk);
		String refreshToken = jwtTokenProvider.generateRefreshToken(userPk);

		return new TokenResponse(accessToken, refreshToken);
	}

	private User mapToUser(JoinRequest joinRequest) {
		return new User(joinRequest.getUserId(), joinRequest.getPassword());
	}

	private void validateUniqueUserId(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);
		if (optionalUser.isPresent()) {
			throw new JoinException();
		}
	}

	public TokenResponse login(LoginRequest joinRequest1) {
		return null;
	}
	// 회원 가입 기능  --- Response OK
	// 멤버 도메인 정의 -> O
	// 멤버 가입 기능 ->
	// userId 중복 확인 테스트
	// 비밀번호 Secret 하게 암호화 하는 기능 BCy~~

	// 로그인 기능 -> /auth/login
	// User 정보 조회, 비밀번호 확인 테스트

	// Access Token과 Refresh Token을 함께 전달.
	// Access Token 발급 기능   END
	// Refresh Token 발급 기능. END

	// Access 토큰 재 발급 기능 (Refresh 토큰 필요) -> Access 토큰 재발급시 Refresh 토큰 재발급
	//auth/access-token

	// 토큰을 DB에 저장
}
