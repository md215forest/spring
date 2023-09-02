package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.TrackHits;
import jp.co.practice.dto.contents.ContentsSearchDto;
import jp.co.practice.dto.contents.SearchResultsDto;
import jp.co.practice.dto.elasticsearch.ContentsSourceResponseDto;
import jp.co.practice.repository.elasticsearch.ContentsSearchRepository;
import jp.co.practice.repository.elasticsearch.response.ContentsSourceResponse;
import jp.co.practice.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class ContentsSearchRepositoryImpl implements ContentsSearchRepository {
    private final ElasticsearchClient elasticsearchClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchResultsDto search(ContentsSearchDto dto) throws IOException {
        String ALIAS_NAME = PropertyUtil.getConstantValue("elasticsearch.contents.alias.name");
        String AGGREGATION_NAME = "group_by_category";

        SearchRequest request = getRequest(ALIAS_NAME, dto, AGGREGATION_NAME);
        ElasticsearchLogUtil.searchRequestLog(ALIAS_NAME, request);

        SearchResponse<ContentsSourceResponse> searchResponse = elasticsearchClient.search(request
                , ContentsSourceResponse.class);

        // 検索結果合計件数
        final long total;
        if (ObjectUtils.isEmpty(searchResponse.hits().total())) {
            total = 0L;
        } else {
            total = searchResponse.hits().total().value();
        }

        List<String> highlightWordList = new ArrayList<>();
        dto.getSearchWordList().forEach(searchWord -> {
            highlightWordList.add(searchWord.getWord());
            if (searchWord.isTranslated()) {
                highlightWordList.add(searchWord.getTranslatedWord());
            }
        });
        dto.getSynonymList().forEach(synonymWord -> synonymWord.getSynonymWordList().forEach(searchWord -> highlightWordList.add(searchWord.getWord())));
        // 検索結果
        List<ContentsSourceResponseDto> contentsList = searchResponse.hits().hits().stream()
                .filter(hit -> !ObjectUtils.isEmpty(hit.source()))
                .map(hit -> {
                    ContentsSourceResponseDto responseDto = ContentsSourceResponseDto.of(hit.source(), hit.score());
                    highlightWordList.stream().distinct().forEach(responseDto::setHighLight);
                    return responseDto;
                })
                .collect(Collectors.toList());

        return SearchResultsDto.of(total, dto.getPage(), dto.getDisplay().getNumber(), contentsList, dto.getSearchWordList());
    }

    /**
     * 検索はパラメータの設定を切り替える可能性があるためbuildメソッドを切り分けている
     */
    private SearchRequest getRequest(String aliasName, ContentsSearchDto dto, String aggregationName) {

        SearchRequest.Builder builder = new SearchRequest.Builder();
        builder.index(aliasName);
        // 検索結果は全件対象にする
        builder.trackTotalHits(TrackHits.of(t -> t
                .enabled(true)));
        // 検索条件の設定
        builder.query(dto.getQuery());
        // 取得件数
        builder.size(dto.getDisplay().getNumber());
        // 開始位置
        builder.from(dto.getFrom());
        // ソート条件
        builder.sort(dto.getSort());
        // フィールドを全取得する
        builder.source(dto.getSource());
        // ハイライトの設定
        builder.highlight(dto.getHighLight());
//        builder.minScore(150.0);
        return builder.build();
    }
}
