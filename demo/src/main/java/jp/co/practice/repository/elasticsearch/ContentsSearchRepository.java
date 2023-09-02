package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.contents.ContentsSearchDto;
import jp.co.practice.dto.contents.SearchResultsDto;

import java.io.IOException;

/**
 * コンテンツ検索 Repository
 */
public interface ContentsSearchRepository {

    /**
     * Elasticsearchで検索を行う
     * @param dto 検索条件
     * @return 検索結果 DTO
     */
    SearchResultsDto search(ContentsSearchDto dto) throws IOException;
}

