package softeer.h9.hey.service.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softeer.h9.hey.domain.car.Trim;
import softeer.h9.hey.dto.car.response.TrimsResponse;
import softeer.h9.hey.repository.car.TrimRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrimService {

    private final TrimRepository trimRepository;
    
    public TrimsResponse findTrimsByModelId(int modelId) {
        List<Trim> trims = trimRepository.findTrimsByModelId(modelId);
        return TrimsResponse.of(trims);
    }
}
