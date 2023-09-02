package jp.co.practice.config;

import com.deepl.api.Translator;
import jp.co.practice.util.PropertyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Translator Configuration
 */
@Configuration
public class DeepLConfig {

    @Bean
    public Translator translator() {
        return new Translator(PropertyUtil.getApplicationValue("deepl.auth.key"));
    }
}
