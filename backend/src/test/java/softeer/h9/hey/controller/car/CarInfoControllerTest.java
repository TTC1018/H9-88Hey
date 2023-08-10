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

@DisplayName("CarInfo Controller 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class CarInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("자동차 코드 요청시 응답 데이터 검증")
    @Test
    void responseTrimTest() throws Exception {
        mockMvc.perform(
                        get("/car/car-code")
                                .param("trim_id", "2")
                                .param("engine_id", "2")
                                .param("body_type_id", "2")
                                .param("wheel_drive_id", "2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.statusCode").value(200),
                        jsonPath("$.data.carCode").value("LXJJ8MAA5")
                );
    }
}
