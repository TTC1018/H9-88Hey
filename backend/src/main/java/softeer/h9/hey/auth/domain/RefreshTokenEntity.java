package softeer.h9.hey.auth.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenEntity {
	private int id;
	private int userId;
	private String refreshToken;
	private LocalDateTime expiredTime;

	public RefreshTokenEntity(int userId, String refreshToken, LocalDateTime expiredTime) {
		this.userId = userId;
		this.refreshToken = refreshToken;
		this.expiredTime = expiredTime;
	}
}
