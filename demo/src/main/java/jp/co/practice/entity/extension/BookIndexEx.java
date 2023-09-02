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
public class BookIndexEx extends BookIndex {

    /** 書籍タイトル */
    @Column(name = "book_title")
    private String bookTitle;

    /** ISBN */
    @Column(name = "isbn")
    private String isbn;
}
