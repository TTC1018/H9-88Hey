package softeer.h9.hey.auth.repository;

import java.util.Optional;

import softeer.h9.hey.auth.domain.User;

public interface UserRepository {
	User save(User user);

	Optional<User> findByUserId(String userId);
}
