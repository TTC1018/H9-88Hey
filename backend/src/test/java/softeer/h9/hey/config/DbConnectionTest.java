package softeer.h9.hey.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@DisplayName("DB 연결 테스트")
class DbConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("DB 연결을 테스트한다.")
    public void testConnection() {
        String result = jdbcTemplate.queryForObject("SELECT 'TEST'", String.class);
        Assertions.assertEquals("TEST", result);
    }
}
