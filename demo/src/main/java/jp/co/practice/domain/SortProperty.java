package jp.co.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;

/**
 * ソートプロパティ
 */
@AllArgsConstructor
@Getter
public enum SortProperty {
    INFORMATION__DELIVERY_TIME_START("information.delivery_time_start"),
    INFORMATION_ID("information.id");


    private final String property;

    /**
     * コンストラクタ
     * @param ordinal 序数
     */
    public static SortProperty of(Integer ordinal) {
        if (ObjectUtils.isEmpty(ordinal)) {
            return null;
        }
        return Arrays.stream(SortProperty.values())
                .filter(element -> ordinal.equals(element.ordinal()))
                .findFirst()
                .orElse(null);
    }
}

