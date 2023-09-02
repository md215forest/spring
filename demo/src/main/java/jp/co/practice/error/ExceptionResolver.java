package jp.co.practice.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Exception ハンドリング
 */
@Component
@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {

    /**
     * 必要なエラーハンドリングを行い、それ以外は{@link org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver}に処理させる
     */
    @Override
    @SneakyThrows
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        throw ex;
    }
}