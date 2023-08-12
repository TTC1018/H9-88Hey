package softeer.h9.hey.repository.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("AvailableColor Repository 테스트")
public class AvailableColorRepositoryTest {

	@Autowired
	private AvailableColorRepository availableColorRepository;

	@Test
	@DisplayName("trim id에 따른 조합 ")
	void findAllByTrimId() {
		int trimId = 3;
		int exteriorColorId = 1;

		List<Integer> availableColors = availableColorRepository.findAllByTrimIdAndExteriorColor(trimId,
			exteriorColorId);

		assertAll(
			() -> assertNotNull(availableColors),
			() -> assertEquals(3, availableColors.size())
		);
	}
}
