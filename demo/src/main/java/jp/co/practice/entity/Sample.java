package jp.co.practice.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * Entity
 */
@Entity
@Table(name = "sample")
@Getter
@Setter
public class Sample {

    /** 管理ID */
    @Id
    @Column(name = "id")
    private Long id;

    /** データ */
    @Column(name = "data")
    private String data;
}
