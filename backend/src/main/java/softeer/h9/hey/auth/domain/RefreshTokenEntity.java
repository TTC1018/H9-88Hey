package softeer.h9.hey.auth.domain;

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

	public RefreshTokenEntity(int userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
}
