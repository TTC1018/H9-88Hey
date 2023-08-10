package softeer.h9.hey.dto.car.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelImagesResponse {
    private final List<String> imageUrls;

    private ModelImagesResponse(final List<String> imageUrls){
        this.imageUrls = imageUrls;
    }

    public static ModelImagesResponse of(final List<String> imageUrls) {
        return new ModelImagesResponse(imageUrls);
    }
}
