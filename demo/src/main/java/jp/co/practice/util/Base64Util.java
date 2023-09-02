package jp.co.practice.util;

import java.util.Base64;

/**
 * Base64 Util
 */
public class Base64Util {

    /**
     * 指定のデータを暗号化して返します。
     * @param data バイト配列のデータ
     * @return 暗号化後の文字列
     */
    public static String urlEncodeToString(byte[] data) {
        return getUrlEncoder().encodeToString(data);
    }

    /**
     * 指定のデータを復号化して返します。
     * @param data バイト配列のデータ
     * @return 復号化後のバイト配列データ
     */
    public static byte[] urlDecodeOrThrow(byte[] data) throws IllegalArgumentException {
        return getUrlDecoder().decode(data);
    }

    /**
     * 指定の暗号化文字列を復号化して返します。
     * @param cipherText 暗号化文字列
     * @return 復号化した文字列
     */
    public static byte[] urlDecodeOrThrow(String cipherText) throws IllegalArgumentException {
        return urlDecodeOrThrow(cipherText.getBytes());
    }

    private static Base64.Encoder getUrlEncoder() {
        return Base64.getUrlEncoder();
    }

    private static Base64.Decoder getUrlDecoder() {
        return Base64.getUrlDecoder();
    }
}
