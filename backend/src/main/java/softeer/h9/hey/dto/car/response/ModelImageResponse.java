package softeer.h9.hey.dto.car.response;

import java.util.List;

public class ModelImageResponse {
    private final List<String> imageUrls;

    private ModelImageResponse(List<String> imageUrls){
        this.imageUrls = imageUrls;
    }

    public static ModelImageResponse of(List<String> imageUrls) {
        return new ModelImageResponse(imageUrls);
    }
}
