package softeer.h9.hey.auth.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import softeer.h9.hey.auth.domain.RefreshTokenEntity;
import softeer.h9.hey.auth.repository.RefreshTokenRepository;

@Component
@RequiredArgsConstructor
public class RefreshTokenAsyncExecutor {
	private final RefreshTokenRepository refreshTokenRepository;

	@Async
	void saveAsync(RefreshTokenEntity tokenEntity) {
		refreshTokenRepository.save(tokenEntity);
	}

	@Async
	public void deleteByIdAsync(int tokenId) {
		refreshTokenRepository.deleteById(tokenId);
	}
}
