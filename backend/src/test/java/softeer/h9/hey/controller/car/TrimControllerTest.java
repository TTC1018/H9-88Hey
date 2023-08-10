package softeer.h9.hey.controller.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Trim Controller 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class TrimControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("트림 조회 요청시 응답 데이터 검증")
    @Test
    void responseTrimTest() throws Exception {
        int modelId = 1;

        mockMvc.perform(
                        get("/car/model/" + modelId + "/trim")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.statusCode").value(200),
                        jsonPath("$.data.trims[0]").exists(),
                        jsonPath("$.data.trims[0].id").value(1),
                        jsonPath("$.data.trims[0].name").value("Le Blanc"),
                        jsonPath("$.data.trims[0].price").value(41980000),
                        jsonPath("$.data.trims[0].trimFeatures[0].name").exists(),
                        jsonPath("$.data.trims[0].trimFeatures[0].imageURL").exists(),

                        jsonPath("$.data.trims[1]").exists(),
                        jsonPath("$.data.trims[1].trimFeatures[2]").exists(),
                        jsonPath("$.data.trims[2]").exists(),
                        jsonPath("$.data.trims[2].trimFeatures[2]").exists(),
                        jsonPath("$.data.trims[3]").exists(),
                        jsonPath("$.data.trims[3].trimFeatures[2]").exists()
                        );
    }
}
