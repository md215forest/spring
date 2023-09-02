package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * 会員閲覧コンテンツ拡張エンティティ
 */
@Entity
@Getter
@Setter
public class UserReadContentsEx extends UserReadContents {

    /** コンテンツID */
    @Column(name = "contents_id")
    private String contentsId;
}
