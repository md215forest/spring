package jp.co.practice.security;

import jp.co.practice.dto.SampleDto;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

@Getter
public class LoginUser extends User {

    // 適宜ユーザーテーブルに置き換える
    private final SampleDto sample;

    public LoginUser(SampleDto sample) {
        super("username", "password", new ArrayList<>());
        this.sample = sample;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return "username";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
