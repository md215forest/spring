package jp.co.practice.repository.elasticsearch.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jp.co.practice.constant.DateTimeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * コンテンツ検索結果 Response
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsSourceResponse {
    /** コンテンツID */
    @JsonProperty("contents_id")
    private String contentsId;

    /** コンテンツ詳細URL */
    @JsonProperty("contents_url")
    private String contentsUrl;

    /** 書籍タイトル */
    private String title;

    /** ヘッダコンテンツ */
    private String header;

    /** メインコンテンツ */
    private String contents;

    /** フッタコンテンツ */
    private String footer;

    /** 出典情報（初出） */
    @JsonProperty("first_appearance")
    private String firstAppearance;

    /** 出典情報（発行日） */
    @JsonProperty("issue_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = DateTimeConstant.DATE_HYPHEN)
    private LocalDate issueDate;

    /** 出典情報（出版社） */
    private String publisher;

    /** 参照ID */
    @JsonProperty("reference_id")
    private String referenceId;

    /** キーワード */
    private List<String> keyword;

    /** 属性 */
    private List<String> attribute;

    /** カテゴリー */
    private List<String> category;

    /** 著者 */
    private List<String> author;

    /** ハッシュタグ */
    private List<String> tag;

    /** パンくずデータ */
    private String breadcrumb;

    /** 閲覧数 */
    @JsonProperty("pv_total")
    private Long pvTotal;

}
