package jp.co.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * HTMLテンプレート Configuration
 */
@Configuration
public class HtmlTemplateConfig {

    @Bean
    @Primary
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        // TODO: 本番と、STGでキャッシュの設定はかえる
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    @Primary
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    @Primary
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

}
