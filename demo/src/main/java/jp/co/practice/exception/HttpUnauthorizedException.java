package jp.co.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HTTP 401 Unauthorized を明示的に通知する実行時例外
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class HttpUnauthorizedException extends RuntimeException {

    /** シリアルバージョン番号 */
    private static final long serialVersionUID = 5413621071864782916L;

    /**
     * メッセージから実行時例外を生成します
     * @param message エラーメッセージ
     */
    public HttpUnauthorizedException(String message) {
        super(message);
    }

    /**
     * 例外情報から実行時例外を生成します
     * @param e オリジナルの例外
     */
    public HttpUnauthorizedException(Exception e) {
        super(e);
    }
}