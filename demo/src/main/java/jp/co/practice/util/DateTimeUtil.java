package jp.co.practice.util;

import org.apache.commons.lang3.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日時に関する汎用メソッドクラス
 */
public class DateTimeUtil {

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");

    /**
     * エポック秒から{@link LocalDateTime}に変換します。
     * @param milliSeconds エポックミリ秒
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertToLocalDateTime(Long milliSeconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(milliSeconds), ZONE_ID);
    }

    /**
     * {@link LocalDateTime}からエポック秒に変換します。
     * @param dateTime {@link LocalDateTime}
     * @return エポック秒
     */
    public static Long convertToEpochSecond(LocalDateTime dateTime) {
        return dateTime.atZone(ZONE_ID).toEpochSecond();
    }

    /**
     * LocalDateTimeを指定のフォーマットで変換
     * @param date
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime date, String format) {
        String result;
        if (date == null) {
            return null;
        }
        try {
            result = DateTimeFormatter.ofPattern(format).format(date);
        } catch (IllegalArgumentException ignore) {
            return null;
        }
        return result;
    }

    /**
     * 指定年~指定追加年までのリストを取得
     * @param targetDate
     * @param addYear
     * @return
     */
    public static List<Integer> getYearListByTargetYearToAddYear(LocalDateTime targetDate, Integer addYear) {
        if (ObjectUtils.isEmpty(targetDate)) {
            return null;
        }

        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i <= addYear; i++) {
            yearList.add(targetDate.getYear() + i);
        }

        return yearList;
    }

    /**
     * 指定年~指定追加年までのリストを取得
     * @param targetDate
     * @param addYear
     * @return
     */
    public static List<Integer> getYearListByTargetYearToAddYear(LocalDateTime targetDate, Integer addYear, Integer startYear) {
        if (ObjectUtils.isEmpty(targetDate)) {
            return null;
        }

        List<Integer> yearList = new ArrayList<>();
        Integer lastYear = startYear;
        while (lastYear <= targetDate.getYear() + addYear) {
            yearList.add(lastYear);
            lastYear++;
        }
        return yearList;
    }
}

