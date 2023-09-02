package jp.co.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO
 */
@AllArgsConstructor
@Getter
public class SampleDto {

    /** 管理ID */
    private Long id;

    /** サンプル値 */
    private String value;

    public static SampleDto of(Long id, String value) {
        return new SampleDto(
                id,
                value
        );
    }

    public boolean isInsert() {
        return id == null;
    }
}
