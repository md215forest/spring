package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.AuthenticationLogRequestDto;

import java.io.IOException;

/**
 * 認証履歴 Repository
 */
public interface AuthenticationLogRepository {

    /**
     * 認証履歴の登録
     * @param dto 人料履歴登録リクエストDTO
     */
    void insert(AuthenticationLogRequestDto dto) throws IOException;
}
