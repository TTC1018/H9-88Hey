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
	private String name;

	public User(String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}
}
