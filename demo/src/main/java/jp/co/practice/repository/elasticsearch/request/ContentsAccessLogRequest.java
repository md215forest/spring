package jp.co.practice.repository.elasticsearch.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jp.co.practice.constant.DateTimeConstant;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * コンテンツ参照履歴 Request
 */
@AllArgsConstructor
public class ContentsAccessLogRequest {

    /** ユーザーID */
    @JsonProperty("user_id")
    private Long userId;

    /** ユーザー名 */
    @JsonProperty("user_name")
    private String userName;

    /** 医学書院ID */
    @JsonProperty("igaku_shoin_id")
    private String igakuShoinId;

    /** メールアドレス */
    @JsonProperty("mail")
    private String mail;

    /** コンテンツID */
    @JsonProperty("contents_id")
    private String contentsId;

    /** 書籍タイトル */
    @JsonProperty("book_title")
    private String bookTitle;

    /** タイトル */
    @JsonProperty("title")
    private String title;

    /** session id */
    @JsonProperty("session_id")
    private String sessionId;

    /** 属性 */
    @JsonProperty("attribute")
    private List<String> attribute;

    /** アクセス日時 */
    @JsonProperty("access_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)
    private LocalDateTime accessTime;
}
