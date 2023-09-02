package jp.co.practice.security.costom;

import jp.co.practice.security.LoginUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 医学書院SSO認証処理用Tokenクラス
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    private final LoginUser loginUser;

    public CustomAuthenticationToken(String token, LoginUser user) {
        super(null);
        this.token = token;
        this.loginUser = user;
    }

    public CustomAuthenticationToken(LoginUser user, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.loginUser = user;
        this.token = token;
        super.setAuthenticated(true);
    }

    public static CustomAuthenticationToken unauthenticated(String token, LoginUser user) {
        return new CustomAuthenticationToken(token,user);
    }

    public static CustomAuthenticationToken authenticated(LoginUser user, String token, Collection<? extends GrantedAuthority> authorities) {
        return new CustomAuthenticationToken(user, token, authorities);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return loginUser;
    }
}
