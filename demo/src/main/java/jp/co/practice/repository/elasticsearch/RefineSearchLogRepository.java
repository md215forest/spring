package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.RefineSearchLogRequestDto;

import java.io.IOException;

/**
 * 再検索履歴 Repository
 */
public interface RefineSearchLogRepository {

    /**
     * 再検索履歴の登録
     * @param dto 再検索履歴リクエストDTO
     */
    void insert(RefineSearchLogRequestDto dto) throws IOException;
}
