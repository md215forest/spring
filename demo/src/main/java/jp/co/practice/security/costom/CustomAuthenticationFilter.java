package jp.co.practice.security.costom;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

/**
 * SSO用のtokenを取得し認証を行うためのFilter
 */
@Slf4j
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/token", "GET");

    public CustomAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("GET")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String token = request.getParameter("token");
        log.debug("receive token:{}", token);

        CustomAuthenticationToken authRequest = CustomAuthenticationToken.unauthenticated(token,null);

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    protected void setDetails(HttpServletRequest request, CustomAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
