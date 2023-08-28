package softeer.h9.hey.service.car;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.Trim;
import softeer.h9.hey.dto.car.response.TrimsResponse;
import softeer.h9.hey.repository.car.TrimRepository;

@Service
@RequiredArgsConstructor
public class TrimService {

	private final TrimRepository trimRepository;

	public TrimsResponse findTrimsByModelId(final int modelId) {
		List<Trim> trims = trimRepository.findTrimsByModelId(modelId);
		return TrimsResponse.of(trims);
	}
}
