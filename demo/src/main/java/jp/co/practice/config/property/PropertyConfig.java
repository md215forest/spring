package jp.co.practice.config.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource(value = {"classpath:constants.properties", "classpath:messages.properties"}, encoding = "UTF-8")
public class PropertyConfig implements WebMvcConfigurer {

    /**
     * プロパティファイルプレースホルダ設定
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
