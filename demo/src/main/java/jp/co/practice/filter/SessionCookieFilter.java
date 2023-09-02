package jp.co.practice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

/**
 * SessionID Filter
 */
@Slf4j
public class SessionCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        Optional<Cookie> cookie = request.getCookies() == null ? Optional.empty() : Arrays.stream(request.getCookies())
                .filter(cookie1 -> StringUtils.equals(cookie1.getName(), "session_id"))
                .findFirst();

        if (cookie.isEmpty()) {
            String randomSessionId = UUID.randomUUID().toString();
            // 独自セッションの生成
            Cookie sessionCookie = new Cookie("session_id", randomSessionId);
            // Cookieの残存期間（秒数）
            sessionCookie.setMaxAge(24 * 60 * 60);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
        }
        filterChain.doFilter(request, response);
    }
}
