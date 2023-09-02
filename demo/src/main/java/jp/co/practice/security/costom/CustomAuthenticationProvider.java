package jp.co.practice.security.costom;

import jp.co.practice.dto.SampleDto;
import jp.co.practice.security.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * SSO認証用Provider
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getCredentials();
        log.debug("authenticate token:{}", token);

        return CustomAuthenticationToken.authenticated(new LoginUser(SampleDto.of(1L, "")), token, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
