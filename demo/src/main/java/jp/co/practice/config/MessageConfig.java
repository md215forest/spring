package jp.co.practice.config;

import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        val bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("classpath:messages");
        bean.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return bean;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        val bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}