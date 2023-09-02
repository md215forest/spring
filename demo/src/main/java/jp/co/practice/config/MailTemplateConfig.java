package jp.co.practice.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * メールテンプレートに関するConfiguration
 */
@Configuration
public class MailTemplateConfig {

    private final TemplateMode templateMode = TemplateMode.TEXT;
    private final Charset charset = StandardCharsets.UTF_8;

    /**
     * メールのテンプレートエンジンを使用するための設定インスタンスを生成します。
     * @return {@link ClassLoaderTemplateResolver}
     */
    @Bean(name = "mailTemplateResolver")
    public ClassLoaderTemplateResolver mailTemplateResolver() {
        val templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(templateMode);
        templateResolver.setCharacterEncoding(charset.name());
        return templateResolver;
    }

    /**
     * メールのテンプレートエンジンを使用するためのインスタンスを生成します。
     * @return {@link SpringTemplateEngine}
     */
    @Bean(name = "mailTemplateEngine")
    public SpringTemplateEngine mailTemplateEngine(@Qualifier("mailTemplateResolver") ClassLoaderTemplateResolver classLoaderTemplateResolver) {
        val templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(classLoaderTemplateResolver);
        return templateEngine;
    }
}