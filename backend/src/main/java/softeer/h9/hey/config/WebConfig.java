package softeer.h9.hey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public static final int PRE_FLIGHT_CACHE_SECOND = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns("*") // 수정 필요!
			.allowCredentials(true)
			.allowedOrigins("*")
			.allowedMethods("*")
			.allowedHeaders("*")
			.maxAge(PRE_FLIGHT_CACHE_SECOND);
	}
}
