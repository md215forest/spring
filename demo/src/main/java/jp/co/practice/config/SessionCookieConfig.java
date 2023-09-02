package jp.co.practice.config;

import jp.co.practice.filter.SessionCookieFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SessionID Config
 */
@Configuration
public class SessionCookieConfig {

    @Bean
    public FilterRegistrationBean<SessionCookieFilter> sessionCookieFilter(){
        return new FilterRegistrationBean<>(new SessionCookieFilter());
    }
}
