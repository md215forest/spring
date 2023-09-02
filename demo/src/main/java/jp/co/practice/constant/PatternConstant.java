package jp.co.practice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 正規表現パターン Constant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatternConstant {

    /** 半角数字 */
    public static final String HALF_NUMERIC = "^[0-9]*$";
    /** 半角英数字 */
    public static final String HALF_ALPHANUMERIC = "^[a-zA-Z0-9]*$";
    /** 年月 */
    public static final String YEAR_MONTH = "^[0-9]{6}*$";
    /** 半角英数記号 */
    public static final String HALF_WIDTH_ALPHA_NUMERIC_SYMBOL = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$";
    /** メールアドレス_フォーマット */
    public static final String MAIL_ADDRESS_FORMAT = "(^([a-zA-Z0-9])([\\.\\+_a-zA-Z0-9-])*@[a-zA-Z0-9]([_a-zA-Z0-9-])*(\\.[_a-zA-Z0-9-]+)*(\\.[_a-zA-Z0-9-]{2,})+$){0,1}";
    /** メールアドレス_フォーマット追加分1(..を含まない) */
    public static final String MAIL_ADDRESS_FORMAT_ADD1 = "^(?!.*\\.\\.).*$";
    /** メールアドレス_フォーマット追加分2(.@を含まない) */
    public static final String MAIL_ADDRESS_FORMAT_ADD2 = "^(?!.*\\.@).*$";
    /** 半角英数字と記号「- _ . ! ~ * ( ) & = + $ ,#%<>?@¥」 */
    public static final String HALF_STRING_AND_SOME_SYMBOL = "^[0-9a-zA-Z\\-\\_\\.\\!\\~\\*\\(\\)\\&\\=\\+\\$\\,\\#\\%\\<\\>\\?\\@\\¥]+$";
    /** URL_フォーマット */
    public static final String URL_FORMAT = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";

}
