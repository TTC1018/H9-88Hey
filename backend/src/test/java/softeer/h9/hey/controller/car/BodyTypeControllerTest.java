package softeer.h9.hey.controller.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BodyTypeController 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class BodyTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("모델에 따른 구동 방식을 조회한다")
    void findALlByModelId() throws Exception {
        int modelId = 1;

        mockMvc.perform(
                get("/car/model/{modelId}/body-type", modelId)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.statusCode").value(200),
                        jsonPath("$.data.bodyTypes[0]").exists(),
                        jsonPath("$.data.bodyTypes[0].id").value(1),
                        jsonPath("$.data.bodyTypes[0].name").value("7인승"),
                        jsonPath("$.data.bodyTypes[0].additionalPrice").value(0),
                        jsonPath("$.data.bodyTypes[0].description").value("기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다")
                );
    }
}
