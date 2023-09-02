package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * 書籍コンテンツ図表情報拡張エンティティ
 */
@Entity
@Getter
@Setter
public class BookContentsImageEx extends BookContentsImage {

    /** 書籍ID */
    @Column(name = "book_id")
    private String bookId;
}
