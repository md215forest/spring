package jp.co.practice.entity.extension;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

/**
 * トップページコンテンツ拡張エンティティ
 */
@Entity
@Getter
@Setter
public class ToppageContentsEx extends ToppageContents {

    /** タイトル */
    @Column(name = "title")
    private String title;

    /** コンテンツ数 */
    @Column(name = "contents")
    private int contents;

    /** 画像データ */
    @Column(name = "image_content")
    private String imageContent;

    /** インデックスタイトル */
    @Column(name = "index_title")
    private String indexTitle;
}