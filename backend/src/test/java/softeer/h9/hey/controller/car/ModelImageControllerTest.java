package softeer.h9.hey.controller.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ModelImageController 테스트")
class ModelImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("모델의 대표 이미지를 요청하면 이미지 링크를 포함한 응답을 반환해야 한다.")
    void modelImage() throws Exception {
        int modelId = 1;

        mockMvc.perform(
                    get("car/model/" + modelId + "/image")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.data.carImageURL").exists(),
                        jsonPath("$.data.carImageURL[0]").value("url"),
                        jsonPath("$.data.carImageURL[1]").value("url"),
                        jsonPath("$.data.carImageURL[2]").value("url")
                );
    }

}