package softeer.h9.hey.service.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import softeer.h9.hey.domain.car.ModelImage;
import softeer.h9.hey.dto.car.response.ModelImagesResponse;
import softeer.h9.hey.repository.car.ModelImageRepository;

@Service
@RequiredArgsConstructor
public class ModelImageService {

	private final ModelImageRepository modelImageRepository;

	public ModelImagesResponse findModelImageUrlsByModelId(final int modelId) {
		List<ModelImage> modelImages = modelImageRepository.findAllByModelId(modelId);

		List<String> imageUrls = new ArrayList<>();
		for (ModelImage modelImage : modelImages) {
			imageUrls.add(modelImage.getImageUrl());
		}

		return ModelImagesResponse.of(imageUrls);
	}
}
