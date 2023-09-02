package jp.co.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HTTP 400 Bad request を明示的に通知する実行時例外
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HttpBadRequestException extends RuntimeException {

    /** シリアルバージョン番号 */
    private static final long serialVersionUID = 2146165319702279172L;

    /**
     * メッセージから実行時例外を生成します
     * @param message エラーメッセージ
     */
    public HttpBadRequestException(String message) {
        super(message);
    }

    /**
     * 例外情報から実行時例外を生成します
     * @param e オリジナルの例外
     */
    public HttpBadRequestException(Exception e) {
        super(e);
    }
}