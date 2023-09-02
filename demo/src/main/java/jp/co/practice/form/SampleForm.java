package jp.co.practice.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Form
 */
@Data
@AllArgsConstructor
public class SampleForm implements Serializable {

    /** サンプル値 */
    private String value;

    /**
     * 初期化コンストラクタ
     */
    public static SampleForm init() {
        return new SampleForm(
                ""
        );
    }
}