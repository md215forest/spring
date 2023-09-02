package jp.co.practice.exception;

/**
 * 外部API失敗 Exception
 */
public class FailedExternalApiException extends RuntimeException {

    String requestParam;
    String responseParam;

    public FailedExternalApiException(String errorMsg, String requestParam, String responseParam) {
        this(errorMsg, requestParam, responseParam, null);
    }

    public FailedExternalApiException(String errorMsg, String requestParam, String responseParam, Throwable exception) {
        super("外部APIエラー：   " + errorMsg, exception);
        this.requestParam = requestParam;
        this.responseParam = responseParam;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public String getResponseParam() {
        return responseParam;
    }
}