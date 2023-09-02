package jp.co.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HTTP 404 Not found を明示的に通知する実行時例外
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HttpNotFoundException extends RuntimeException {

    /** シリアルバージョン番号 */
    private static final long serialVersionUID = -7146624975154952576L;

    /**
     * メッセージから実行時例外を生成します
     * @param message エラーメッセージ
     */
    public HttpNotFoundException(String message) {
        super(message);
    }
}