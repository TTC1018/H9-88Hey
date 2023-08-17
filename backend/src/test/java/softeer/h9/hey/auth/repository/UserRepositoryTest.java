package softeer.h9.hey.auth.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import softeer.h9.hey.auth.domain.User;

@SpringBootTest
@Transactional
@DisplayName("User 저장소 테스트")
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("유저를 저장한다.")
	void createUserTest() {
		User user = new User("userId", "password");

		User savedUser = userRepository.save(user);

		assertThat(savedUser.getUserId()).isNotNull();
	}

	@Test
	@DisplayName("유저 ID를 통해 User를 조회한다.")
	void findUserByIdTest() {
		User user = new User("userId", "password");
		userRepository.save(user);

		Optional<User> findUser = userRepository.findByUserId(user.getUserId());

		assertTrue(findUser.isPresent());
	}
}
