package jp.co.practice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.co.practice.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 認証 Controller
 */
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * ログイン画面を表示します。
     * @return
     */
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model, @AuthenticationPrincipal LoginUser loginUser,
                           @RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           HttpSession session) {

        String target = request.getParameter("target");
        model.addAttribute("showErrorMsg", false);
        model.addAttribute("showLogoutMsg", false);
        if (StringUtils.isNotBlank(target) && loginUser != null) {
            model.addAttribute("username", loginUser.getMail());
            model.addAttribute("target", target);
        }

        if (error != null) {
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (ex != null) {
                    String errorMessage = ex.getMessage();
                    model.addAttribute("username", errorMessage.substring(errorMessage.indexOf(":") + 1).trim());
                    model.addAttribute("showErrorMsg", true);
                    model.addAttribute("errorMsg", ex.getMessage());
                }
            }
        } else if (logout != null) {
            model.addAttribute("showLogoutMsg", true);
        }

        return "auth/input.html";
    }

    /**
     * セッション切れ時用画面
     * @return
     */
    @GetMapping("/session_expired")
    public String sessLost() {
        return "error/sessionExpired.html";
    }
}
