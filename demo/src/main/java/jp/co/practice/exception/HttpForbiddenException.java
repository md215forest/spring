package jp.co.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HTTP 403 Forbidden を明示的に通知する実行時例外
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class HttpForbiddenException extends RuntimeException {

    /** シリアルバージョン番号 */
    private static final long serialVersionUID = 545862944889780030L;

    /**
     * メッセージから実行時例外を生成します。
     * @param message エラーメッセージ
     */
    public HttpForbiddenException(String message) {
        super(message);
    }

    /**
     * 例外情報から実行時例外を生成します。
     * @param e オリジナルの例外
     */
    public HttpForbiddenException(Exception e) {
        super(e);
    }
}