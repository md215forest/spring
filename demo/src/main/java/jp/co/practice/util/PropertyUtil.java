package jp.co.practice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * プロパティファイル Util
 */
public class PropertyUtil {

    /**
     * ファイルタイプ
     */
    @AllArgsConstructor
    @Getter
    private enum PropertyFile {
        APPLICATION("application"),
        CONSTANTS("constants"),
        MESSAGES("messages"),
        ;
        private final String fileName;
    }

    /**
     * アプリケーションプロパティファイルよりキーに該当する値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    public static String getApplicationValue(String key) {
        return getValue(PropertyFile.APPLICATION, key);
    }

    /**
     * アプリケーションプロパティファイルよりキーに該当する値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は例外をスロー
     */
    public static Integer getApplicationIntValueOrElseThrow(String key) {
        return getIntValueOrElseThrow(PropertyFile.APPLICATION, key);
    }


    /**
     * 定数プロパティファイルよりキーに該当する値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    public static String getConstantValue(String key) {
        return getValue(PropertyFile.CONSTANTS, key);
    }

    /**
     * 定数プロパティファイルよりキーに該当するInteger値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は例外をスロー
     */
    public static Long getConstantLongValueOrElseThrow(String key) {
        return getLongValueOrElseThrow(PropertyFile.CONSTANTS, key);
    }


    /**
     * 定数プロパティファイルよりキーに該当するInteger値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は例外をスロー
     */
    public static Integer getConstantIntValueOrElseThrow(String key) {
        return getIntValueOrElseThrow(PropertyFile.CONSTANTS, key);
    }

    /**
     * メッセージプロパティファイルよりキーに該当する値を取得します。
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    public static String getMessageValue(String key) {
        return getValue(PropertyFile.MESSAGES, key);
    }

    /**
     * メッセージプロパティファイルよりキーに該当する値を取得し、引数を埋め込んで返します。
     * @param key キー
     * @param arguments 引数
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    public static String getMessageValue(String key, Object... arguments) {
        return getValue(PropertyFile.MESSAGES, key, arguments);
    }

    /**
     * プロパティファイルよりキーに該当する値を取得します。
     * @param propertyFile プロパティファイル
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    private static String getValue(PropertyFile propertyFile, String key) {
        val bundle = ResourceBundle.getBundle(propertyFile.getFileName());
        val value = bundle.getString(key);
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value;
    }

    /**
     * プロパティファイルよりキーに該当する値を取得し、引数を埋め込んで返します。
     * @param propertyFile プロパティファイル
     * @param key キー
     * @param arguments 引数
     * @return キーが存在する場合は対応する値。存在しない場合は空文字
     */
    private static String getValue(PropertyFile propertyFile, String key, Object... arguments) {
        val value = getValue(propertyFile, key);
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return MessageFormat.format(value, arguments);
    }

    /**
     * プロパティファイルよりキーに該当するInteger値を取得します。
     * @param propertyFile プロパティファイル
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は例外をスロー
     */
    private static Integer getIntValueOrElseThrow(PropertyFile propertyFile, String key) {
        final int intValue;
        try {
            intValue = Integer.parseInt(getValue(propertyFile, key));
        } catch (NumberFormatException e) {
            throw new RuntimeException("プロパティファイルから数値の取得に失敗しました: " + key, e);
        }
        return intValue;
    }

    /**
     * プロパティファイルよりキーに該当するLong値を取得します。
     * @param propertyFile プロパティファイル
     * @param key キー
     * @return キーが存在する場合は対応する値。存在しない場合は例外をスロー
     */
    private static Long getLongValueOrElseThrow(PropertyFile propertyFile, String key) {
        final long longValue;
        try {
            longValue = Long.parseLong(getValue(propertyFile, key));
        } catch (NumberFormatException e) {
            throw new RuntimeException("プロパティファイルから数値の取得に失敗しました: " + key, e);
        }
        return longValue;
    }

}
