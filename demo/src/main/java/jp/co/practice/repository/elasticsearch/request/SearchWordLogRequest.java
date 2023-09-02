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
 * 検索ワード履歴 Request
 */
@AllArgsConstructor
public class SearchWordLogRequest {

    /** ユーザー名 */
    @JsonProperty("user_name")
    private String userName;

    /** 医学書院ID */
    @JsonProperty("igaku_shoin_id")
    private String igakuShoinId;

    /** メールアドレス */
    @JsonProperty("mail")
    private String mail;

    /** 検索ワード */
    @JsonProperty("word")
    private String word;

    /** DeepL変換言語ID */
    @JsonProperty("deepl_language_id")
    private Long deeplLanguageId;

    /** 変換後検索ワード */
    @JsonProperty("translated_word")
    private String translatedWord;

    /** session id */
    @JsonProperty("session_id")
    private String sessionId;

    /** 属性 */
    @JsonProperty("attribute")
    private List<String> attribute;

    /** 検索日時 */
    @JsonProperty("search_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)
    private LocalDateTime searchTime;
}
