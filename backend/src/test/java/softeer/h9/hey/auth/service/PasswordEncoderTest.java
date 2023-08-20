package softeer.h9.hey.auth.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Password Encoder 테스트")
class PasswordEncoderTest {

	private final PasswordEncoder passwordEncoder = new PasswordEncoder();

	@Test
	@DisplayName("패스워드를 인코딩하면 복호화하기 어려운 패스워드로 변환한다.")
	void encodePasswordTest() {
		String password = "12345678";

		String encodedPassword = passwordEncoder.encode(password);

		Assertions.assertNotEquals(password, encodedPassword);
	}

	@Test
	@DisplayName("같은 패스워드는 동일하다고 판단한다.")
	void comparePasswordWithEncodedPassword() {
		String password = "12345678";
		String encodedPassword = passwordEncoder.encode(password);

		Assertions.assertTrue(passwordEncoder.compare(password, encodedPassword));
	}


	@Test
	@DisplayName("비밀번호가 다른 경우 패스워드 불일치로 판단한다.")
	void comparePasswordWithEncodedPassword() {
		String password1 = "12345678";
		String password2 = "abcdefgh";
		String encodedPassword = passwordEncoder.encode(password1);

		Assertions.assertTrue(passwordEncoder.compare(password2, encodedPassword));
	}
}
