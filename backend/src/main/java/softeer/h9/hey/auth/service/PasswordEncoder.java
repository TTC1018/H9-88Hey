package softeer.h9.hey.auth.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordEncoder {
	public static final String FAIL_TO_CONSTRUCT_LOG_MESSAGE = "Fail to construct PasswordEncoder";

	private final String salt;

	private final MessageDigest messageDigest;

	public PasswordEncoder(
		@Value("${password.salt}") String salt,
		@Value("${password.algorithm}") String passwordAlgorithm) {
		this.salt = salt;
		try {
			messageDigest = MessageDigest.getInstance(passwordAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			log.error(FAIL_TO_CONSTRUCT_LOG_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	public String encode(String password) {
		byte[] passwordBytes = (password + salt).getBytes(StandardCharsets.UTF_8);
		byte[] hash = messageDigest.digest(passwordBytes);
		return bytesToHex(hash);
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public boolean compare(String password, String expectedEncodedPassword) {
		String encodedPassword = encode(password);
		return encodedPassword.equals(expectedEncodedPassword);
	}
}
