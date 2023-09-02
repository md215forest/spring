package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * ランキング拡張エンティティ
 */
@Entity
@Getter
@Setter
public class RankingEx extends Ranking {

    /** 書籍ID */
    @Column(name = "book_id")
    private String bookId;

    /** ISBN */
    @Column(name = "isbn")
    private String isbn;
}
