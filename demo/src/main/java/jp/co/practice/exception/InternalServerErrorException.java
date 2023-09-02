package jp.co.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HTTP 500 Internal Server Error を明示的に通知する実行時例外
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    /** シリアルバージョン番号 */
    private static final long serialVersionUID = 4717562630297627552L;

    /**
     * メッセージから実行時例外を生成します
     * @param message エラーメッセージ
     */
    public InternalServerErrorException(String message) {
        super(message);
    }

    /**
     * 例外情報から実行時例外を生成します
     * @param e オリジナルの例外
     */
    public InternalServerErrorException(Exception e) {
        super(e);
    }
}
