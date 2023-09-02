package jp.co.practice.config;

import jp.co.practice.util.PropertyUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * RestTemplate Configuration
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        Duration timeout = Duration.ofSeconds(PropertyUtil.getConstantIntValueOrElseThrow("rest.template.timeout.second"));
        return new RestTemplateBuilder()
                .setConnectTimeout(timeout)
                .setReadTimeout(timeout)
                .build();
    }
}
