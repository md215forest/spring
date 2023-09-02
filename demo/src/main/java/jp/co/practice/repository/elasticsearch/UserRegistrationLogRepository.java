package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.UserRegistrationLogRequestDto;

import java.io.IOException;

/**
 * 会員登録履歴 Repository
 */
public interface UserRegistrationLogRepository {

    /**
     * 会員登録履歴の登録
     * @param dto 会員登録履歴リクエストDTO
     * @throws IOException
     */
    void insert(UserRegistrationLogRequestDto dto) throws IOException;
}
