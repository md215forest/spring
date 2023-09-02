package jp.co.practice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.seasar.doma.Domain;

import java.util.Arrays;
import java.util.Objects;

/**
 * 真偽値
 */
@Domain(valueType = Integer.class, factoryMethod = "of")
@RequiredArgsConstructor
@Getter
public enum Boolean {

    TRUE(1, "true", true),
    FALSE(0, "false", false),
    INVALID(null, null, false);

    private final Integer value;
    private final String label;
    private final java.lang.Boolean bool;

    /**
     * コンストラクタ
     * @param value 値
     */
    public static Boolean of(Integer value) {
        return Arrays.stream(Boolean.values())
                .filter(element -> Objects.equals(element.getValue(), value))
                .findFirst()
                .orElse(INVALID);
    }

    /**
     * コンストラクタ
     * @param value 値（文字列）
     */
    public static Boolean of(String value) {
        final int intValue;
        try {
            intValue = Integer.parseInt(value);
        } catch (Exception e) {
            return INVALID;
        }
        return of(intValue);
    }

    public Integer getValue() {
        return value;
    }
}

