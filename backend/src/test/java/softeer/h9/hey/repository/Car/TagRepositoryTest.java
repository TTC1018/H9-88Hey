package softeer.h9.hey.repository.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Tag Repository 테스트")
class TagRepositoryTest {

	@Autowired
	private TagRepository repository;

	@Test
	@DisplayName("내장 색상 id와 limit를 통해 상위 태그를 조회한다.")
	void findTopByInteriorColorId() {
		int limit = 3;
		int interiorColorId = 1;

		List<String> tags = repository.findTopByInteriorColorId(interiorColorId, limit);

		assertTrue(limit >= tags.size());
	}
}
