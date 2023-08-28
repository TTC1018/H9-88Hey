package softeer.h9.hey.repository.archiving;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import softeer.h9.hey.domain.archiving.SelectedOption;

@SpringBootTest
@DisplayName("ArchivingSelectedOption Repository 테스트")
class ArchivingSelectedOptionRepositoryTest {

	@Autowired
	private ArchivingSelectedOptionRepository repository;

	@DisplayName("아카이빙 id를 기반으로 아카이빙 정보들을 조회한다.")
	@Test
	void findByArchiving() {
		Long id = 479893076433545674L;

		List<SelectedOption> selectOptions = repository.findByArchiving(id);

	}
}
