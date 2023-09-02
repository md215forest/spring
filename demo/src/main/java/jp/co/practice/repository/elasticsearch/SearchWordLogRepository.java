package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.SearchWordLogRequestDto;

import java.io.IOException;

/**
 * 検索ワード履歴 Repository
 */
public interface SearchWordLogRepository {

    /**
     * 検索ワード履歴の登録
     * @param dto 検索ワード履歴リクエストDTO
     * @throws IOException
     */
    void insert(SearchWordLogRequestDto dto) throws IOException;
}
