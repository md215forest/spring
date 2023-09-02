package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * 書籍コンテンツ拡張エンティティ
 */
@Entity
@Getter
@Setter
public class IgsAttributeEx extends IgsAttribute {

    /** 職業 */
    @Column(name = "job_name")
    private String jobName;

    /** 職種 */
    @Column(name = "position_name")
    private String positionName;

    /** 専門分野 */
    @Column(name = "field_name")
    private String fieldName;
}
