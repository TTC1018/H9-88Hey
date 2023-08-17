package softeer.h9.hey.auth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private Integer id;
	private String userId;
	private String password;

	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
