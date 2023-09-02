package jp.co.practice.security;

import jp.co.practice.security.costom.CustomAuthenticationConfigure;
import jp.co.practice.security.costom.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 医療情報提供サービスの認証・認可設定
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    /** リリースレベル **/
    @Value("${release.env}")
    private String releaseEnv;

    @Bean
    public SecurityFilterChain imisSecurityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http.authenticationManager(authenticationManager());

        //ID/PWD認証
        http.securityMatcher("/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/"
                                , "/api/**"
                                , "/login"
                                , "/auth/"
                                , "/token"
                                , "/error"
                                , "/session_expired"
                                , "/assets/**"
                                , "/css/**"
                                , "/js/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .permitAll()
                        .successHandler(userAuthenticationSuccessHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                .requestCache((cache) -> cache
                        .requestCache(requestCache))
                // 同時ログイン数を設定する
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/session_expired"));

        //Token認証
        http.apply(new CustomAuthenticationConfigure<>())
                .successHandler(userAuthenticationSuccessHandler)
                .loginProcessingUrl("/token");

        return http.build();
    }

    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new CustomAuthenticationProvider());
        return new ProviderManager(providers);
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        val resolver = new CookieHttpSessionIdResolver();
        val cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseBase64Encoding(false);
        cookieSerializer.setCookieName("SESSION_USER");
        // 開発環境以外ではsecure属性をつける
        if (!releaseEnv.equals("local") && !releaseEnv.equals("dev")) {
            cookieSerializer.setUseSecureCookie(true);
            cookieSerializer.setUseHttpOnlyCookie(true);
        }
        resolver.setCookieSerializer(cookieSerializer);
        return resolver;
    }
}
