package jp.co.practice.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component("userAuthenticationSuccessHandler")
@Slf4j
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ProfileEditPathResolver profileEditPathResolver;

    public UserAuthenticationSuccessHandler() {
        setDefaultTargetUrl("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> target = profileEditPathResolver.resolve(request, authentication);
        return target.orElseGet(() -> super.determineTargetUrl(request, response, authentication));
    }
}
