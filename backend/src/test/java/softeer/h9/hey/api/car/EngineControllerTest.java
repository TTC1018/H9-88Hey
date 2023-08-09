package softeer.h9.hey.api.car;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@DisplayName("Engine Controller 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class EngineControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("엔진 조회 요청시 응답 데이터 검증")
    @Test
    void engine() throws Exception {
        int modelId = 1;

        mockMvc.perform(
                        get("/car/model/" + modelId + "/trim")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.statusCode").value(200),
                        jsonPath("$.data.engines[0]").exists(),
                        jsonPath("$.data.engines[0].id").value(1),
                        jsonPath("$.data.engines[0].additionalPrice").value(0),
                        jsonPath("$.data.engines[0].modelId").value(1),
                        jsonPath("$.data.engines[0].imageUrl").exists(),
                        jsonPath("$.data.engines[0].description").value("높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다"),
                        jsonPath("$.data.engines[0].maximumPower").value("202/3,800PS/rpm"),
                        jsonPath("$.data.engines[0].maximumTorque").value("45.0/1,750~2,750kgf-m/rpm"),

                        jsonPath("$.data.engines[1]").exists());
    }
}