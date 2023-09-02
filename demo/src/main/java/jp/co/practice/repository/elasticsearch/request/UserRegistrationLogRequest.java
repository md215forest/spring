package jp.co.practice.repository.elasticsearch.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jp.co.practice.constant.DateTimeConstant;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 会員登録ログ Request
 */
@AllArgsConstructor
public class UserRegistrationLogRequest {

    /** ユーザー名 */
    @JsonProperty("user_name")
    private String userName;

    /** 医学書院ID */
    @JsonProperty("igaku_shoin_id")
    private String igakuShoinId;

    /** メールアドレス */
    @JsonProperty("mail")
    private String mail;

    /** 登録日 */
    @JsonProperty("register_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)
    private LocalDateTime registerTime;
}
