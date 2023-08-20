package softeer.h9.hey.auth.service;

import java.util.List;
import java.util.Map;
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
import softeer.h9.hey.auth.exception.InvalidTokenException;
import softeer.h9.hey.auth.exception.JoinException;
import softeer.h9.hey.auth.exception.LoginException;
import softeer.h9.hey.auth.repository.RefreshTokenRepository;
import softeer.h9.hey.auth.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	public static final String USER_NAME = "userName";
	public static final String SUBJECT = "sub";

	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	public TokenResponse join(JoinRequest joinRequest) {
		User user = mapToUser(joinRequest);
		validateUniqueEmail(user.getEmail());

		user = userRepository.save(user);
		return makeTokenResponse(user.getId(), user.getName());
	}

	private User mapToUser(JoinRequest joinRequest) {
		return new User(joinRequest.getEmail(), joinRequest.getPassword(), joinRequest.getUserName());
	}

	private void validateUniqueEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			throw new JoinException();
		}
	}

	public TokenResponse login(LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		User user = getValidatedUser(email);
		checkPassword(password, user);

		return makeTokenResponse(user.getId(), user.getName());
	}

	private TokenResponse makeTokenResponse(int userPk, String userName) {
		Map<String, Object> claims = Map.of(USER_NAME, userName);

		String accessToken = jwtTokenProvider.generateAccessToken(userPk, claims);
		String refreshToken = jwtTokenProvider.generateRefreshToken(userPk, claims);
		refreshTokenRepository.save(new RefreshTokenEntity(userPk, refreshToken));

		return new TokenResponse(accessToken, refreshToken, userName);
	}

	private static void checkPassword(String password, User user) {
		if (!user.getPassword().equals(password)) {
			throw new LoginException();
		}
	}

	private User getValidatedUser(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isEmpty()) {
			throw new LoginException();
		}
		return optionalUser.get();
	}

	public TokenResponse republishAccessToken(AccessTokenRequest accessTokenRequest) {
		String refreshToken = accessTokenRequest.getRefreshToken();

		Map<String, Object> claims = jwtTokenProvider.getClaimsFromToken(refreshToken);
		int userId = Integer.parseInt((String)claims.get(SUBJECT));
		String userName = (String)claims.get(USER_NAME);

		checkAndRemoveRefreshToken(userId, refreshToken);
		return makeTokenResponse(userId, userName);
	}

	private void checkAndRemoveRefreshToken(int userId, String refreshToken) {
		List<RefreshTokenEntity> refreshTokenEntities = refreshTokenRepository.findByUserId(userId);
		Optional<RefreshTokenEntity> optionalRefreshTokenEntity = refreshTokenEntities.stream()
			.filter(refreshTokenEntity -> refreshTokenEntity.getRefreshToken().equals(refreshToken))
			.findAny();
		if (optionalRefreshTokenEntity.isEmpty()) {
			throw new InvalidTokenException();
		}
		RefreshTokenEntity refreshTokenEntity = optionalRefreshTokenEntity.get();
		refreshTokenRepository.deleteById(refreshTokenEntity.getId());
	}
}
