package softeer.h9.hey.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.auth.domain.RefreshTokenEntity;
import softeer.h9.hey.auth.domain.User;
import softeer.h9.hey.auth.dto.request.AccessTokenRequest;
import softeer.h9.hey.auth.dto.request.JoinRequest;
import softeer.h9.hey.auth.dto.request.LoginRequest;
import softeer.h9.hey.auth.dto.response.TokenResponse;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.RefreshTokenRepository;
import softeer.h9.hey.auth.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	public TokenResponse join(JoinRequest joinRequest) {
		User user = mapToUser(joinRequest);
		validateUniqueUserId(user.getUserId());

		user = userRepository.save(user);
		return getTokenResponse(user.getId());
	}

	private User mapToUser(JoinRequest joinRequest) {
		return new User(joinRequest.getUserId(), joinRequest.getPassword(), joinRequest.getUserName());
	}

	private void validateUniqueUserId(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);
		if (optionalUser.isPresent()) {
			throw new JoinException();
		}
	}

	public TokenResponse login(LoginRequest loginRequest) {
		String userId = loginRequest.getUserId();
		String password = loginRequest.getPassword();

		User user = validatedUser(userId);
		checkPassword(password, user);

		return getTokenResponse(user.getId());
	}

	private TokenResponse getTokenResponse(int userId) {
		String userPk = String.valueOf(userId);

		String accessToken = jwtTokenProvider.generateAccessToken(userPk);
		String refreshToken = jwtTokenProvider.generateRefreshToken(userPk);
		refreshTokenRepository.save(new RefreshTokenEntity(Integer.parseInt(userPk), refreshToken));

		return new TokenResponse(accessToken, refreshToken);
	}

	private static void checkPassword(String password, User user) {
		if (!user.getPassword().equals(password)) {
			throw new LoginException();
		}
	}

	private User validatedUser(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);
		if (optionalUser.isEmpty()) {
			throw new LoginException();
		}
		return optionalUser.get();
	}

	public TokenResponse republishAccessToken(AccessTokenRequest accessTokenRequest) {
		String refreshToken = accessTokenRequest.getRefreshToken();
		String userId = jwtTokenProvider.getSubjectFromToken(refreshToken);
		return getTokenResponse(Integer.parseInt(userId));
	}
}
