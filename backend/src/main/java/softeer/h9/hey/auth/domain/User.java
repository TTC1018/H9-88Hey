package softeer.h9.hey.auth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private Integer id;
	private String email;
	private String password;
	private String name;

	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
