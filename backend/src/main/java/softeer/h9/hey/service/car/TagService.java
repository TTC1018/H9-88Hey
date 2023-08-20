package softeer.h9.hey.service.car;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.dto.car.request.TagByExteriorColorIdRequest;
import softeer.h9.hey.dto.car.request.TagByInteriorColorIdRequest;
import softeer.h9.hey.dto.car.request.TagBySelectOptionRequest;
import softeer.h9.hey.dto.car.response.TagResponse;
import softeer.h9.hey.repository.car.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository repository;

	public TagResponse findTopBySelectOptionId(TagBySelectOptionRequest request) {
		String id = request.getId();
		int limit = request.getLimit();

		return TagResponse.from(repository.findTopBySelectOptionId(id, limit));
	}

	public TagResponse findTopByExteriorColorId(TagByExteriorColorIdRequest request) {
		int id = request.getId();
		int limit = request.getLimit();

		return TagResponse.from(repository.findTopByExteriorColorId(id, limit));
	}

	public TagResponse findTopByInteriorColorId(TagByInteriorColorIdRequest request) {
		int id = request.getId();
		int limit = request.getLimit();

		return TagResponse.from(repository.findTopByInteriorColorId(id, limit));
	}
}
