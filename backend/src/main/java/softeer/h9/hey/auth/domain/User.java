package softeer.h9.hey.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class User {
	private Integer id;
	private final String userId;
	private final String password;
}
