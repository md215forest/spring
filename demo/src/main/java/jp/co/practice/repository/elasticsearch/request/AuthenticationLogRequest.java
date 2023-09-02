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
 * 認証履歴 Request
 */
@AllArgsConstructor
public class AuthenticationLogRequest {

    /** ユーザー名 */
    @JsonProperty("user_name")
    private String userName;

    /** 医学書院ID */
    @JsonProperty("igaku_shoin_id")
    private String igakuShoinId;

    /** メールアドレス */
    @JsonProperty("mail")
    private String mail;

    /** 初回アクセス日時 */
    @JsonProperty("first_access_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)
    private LocalDateTime firstAccessTime;

    /** 最終アクセス日時 */
    @JsonProperty("last_access_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)
    private LocalDateTime lastAccessTime;

    /** ライセンス情報 */
    @JsonProperty("license")
    private String license;

    /** タイプ */
    @JsonProperty("type")
    private String type;

    /** 属性 */
    @JsonProperty("attribute")
    private List<String> attribute;
}
