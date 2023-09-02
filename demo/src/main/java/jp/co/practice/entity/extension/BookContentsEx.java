package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import java.time.LocalDate;

/**
 * 書籍コンテンツ拡張エンティティ
 */
@Entity
@Getter
@Setter
public class BookContentsEx extends BookContents {

    /** 書籍ID */
    @Column(name = "book_id")
    private String bookId;

    /** ISBN */
    @Column(name = "isbn")
    private String isbn;

    /** elasticsearch用インデックス識別子 */
    @Column(name = "index_identifier")
    private String indexIdentifier;

    /** 出版日 */
    @Column(name = "pubdate")
    private LocalDate pubdate;

    /** タイトル */
    @Column(name = "book_title")
    private String bookTitle;

    /** 書籍共通ヘッダ */
    @Column(name = "book_base_header")
    private String bookBaseHeader;

    /** コンテンツID */
    @Column(name = "contents_id")
    private String contentsId;

    /** 項目名 */
    @Column(name = "title")
    private String title;

    /** 取込元ディレクトリ */
    @Column(name = "source_path")
    private String sourcePath;

}
