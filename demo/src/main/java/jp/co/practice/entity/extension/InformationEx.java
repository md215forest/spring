package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * お知らせ拡張エンティティ
 */
@Entity
@Getter
@Setter
public class InformationEx extends Information {

    /** 会員ID */
    @Column(name = "user_id")
    private Long userId;
}
