package jp.co.practice.util;

import lombok.val;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 暗号化／復号化 Util
 */
public class CryptUtil {

    private static final String MESSAGE_DIGEST_ALGORITHM = "MD5";
    private static final String SECRET_KEY_ALGORITHM = "DES";
    private static final String SPACE_CHARACTER = "\u0020";
    private static final String ENCRYPTION_KEY_PROPERTY_NAME = "encryption.key";


    /**
     * 指定の平文を暗号化して返します。
     * @param clearText 平文
     * @param defaultValue 失敗時のデフォルト値
     * @return 平文を暗号化した文字列。暗号化失敗の場合はnull
     */
    public static String encryptOrElse(String clearText, String defaultValue) {
        if (StringUtils.isBlank(clearText)) {
            return defaultValue;
        }

        final SecretKey secretKey;
        try {
            secretKey = createSecretKey();
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e) {
            return defaultValue;
        }

        final Cipher cipher;
        try {
            cipher = createCipher();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            return defaultValue;
        }

        final byte[] encryptedData;
        try {
            encryptedData = cipher.doFinal(clearText.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            return defaultValue;
        }
        return Base64Util.urlEncodeToString(encryptedData);
    }

    /**
     * 指定の値を暗号化して返します。
     * @param value 値
     * @param defaultValue 失敗時のデフォルト値
     * @return 値を暗号化した文字列。暗号化失敗の場合はnull
     */
    public static String encryptOrElse(Long value, String defaultValue) {
        if (ObjectUtils.isEmpty(value)) {
            return defaultValue;
        }
        return encryptOrElse(value.toString(), defaultValue);
    }

    /**
     * 指定の暗号化文字列を復号化して整数値で返します。
     * @param cipherText 暗号化文字列
     * @param defaultValue 失敗時のデフォルト値
     * @return 暗号化文字列を復号化した文字列
     */
    public static Long decryptToLongOrElse(String cipherText, Long defaultValue) {
        String decryptedValue = decryptOrElse(
                cipherText, ObjectUtils.isNotEmpty(defaultValue) ? defaultValue.toString() : null);

        final long value;
        try {
            value = Long.parseLong(decryptedValue);
        } catch (Exception e) {
            return defaultValue;
        }

        return value;
    }

    /**
     * 指定の暗号化文字列を復号化して返します。
     * @param cipherText 暗号化文字列
     * @param defaultValue 失敗時のデフォルト値
     * @return 暗号化文字列を復号化した文字列
     */
    public static String decryptOrElse(String cipherText, String defaultValue) {
        if (cipherText == null || "undefined".equals(cipherText)) {
            return defaultValue;
        }
        if (cipherText.contains(SPACE_CHARACTER)) {
            cipherText = cipherText.replaceAll(SPACE_CHARACTER, "+");
        }

        final SecretKey secretKey;
        try {
            secretKey = createSecretKey();
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e) {
            return defaultValue;
        }

        final Cipher cipher;
        try {
            cipher = createCipher();
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            return defaultValue;
        }


        final byte[] decodedData;
        try {
            decodedData = Base64Util.urlDecodeOrThrow(cipherText);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }

        final byte[] decryptedData;
        try {
            decryptedData = cipher.doFinal(decodedData);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            return defaultValue;
        }

        return new String(decryptedData);
    }

    private static SecretKey createSecretKey()
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {

        val encryptionKey = PropertyUtil.getApplicationValue(ENCRYPTION_KEY_PROPERTY_NAME);
        val messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
        messageDigest.update(encryptionKey.getBytes());
        val pssKey = messageDigest.digest();
        val desKeySpec = new DESKeySpec(pssKey);
        val secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    private static Cipher createCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(SECRET_KEY_ALGORITHM);
    }
}
