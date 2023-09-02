package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.ContentsAccessLogRequestDto;

import java.io.IOException;

/**
 * コンテンツ参照履歴 Repository
 */
public interface ContentsAccessLogRepository {

    /**
     * コンテンツ参照履歴の登録
     * @param dto コンテンツ参照履歴リクエストDTO
     */
    void insert(ContentsAccessLogRequestDto dto) throws IOException;
}
