package jp.co.practice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 日付パターン Constant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeConstant {

    public static final String DATE_SLASH = "yyyy/MM/dd";
    public static final String DATE_AND_DAY_SLASH = "yyyy/MM/dd（E）";
    public static final String DATE_TIME_AND_DAY_SLASH = "yyyy/MM/dd（E） HH:mm";
    public static final String DATE_HYPHEN = "yyyy-MM-dd";
    public static final String DATE_PERIOD = "yyyy.MM.dd";
    public static final String MONTH_DAY_SLASH = "MM/dd（E）";
    public static final String TIME_COLON = "HH:mm";
    public static final String MINUTE_COLON = "mm";
    public static final String DATE_TIME_SLASH = "yyyy/MM/dd HH:mm";
    public static final String DATE_TIME_HYPHEN = "yyyy-MM-dd HH:mm";
    public static final String YEAR_MONTH_JAPANESE_PATTERN = "yyyy年M月";
    public static final String MONTH_JAPANESE_PATTERN = "MM月";
    public static final String YEAR_MONTH_SLASH = "yyyy/MM";
    public static final String YEAR_MONTH_DAY_JAPANESE_PATTERN = "yyyy年MM月dd日";
    public static final String YEAR_MONTH_DAY_JAPANESE_PATTERN_TWO = "yyyy年M月d日";
    public static final String SYSTEM_PROPERTY_DATE_TIME = "yyyyMMddHHmmss";
    public static final String SYSTEM_PROPERTY_DATE = "yyyyMMdd";
    public static final String SYSTEM_PROPERTY_DATE_TIME_HYPHEN = "yyyy-MM-dd HH:mm:ss";
    public static final String SYSTEM_PROPERTY_DATE_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";
}
