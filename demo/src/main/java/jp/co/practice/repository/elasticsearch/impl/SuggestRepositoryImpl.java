package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import jp.co.practice.repository.elasticsearch.SuggestRepository;
import jp.co.practice.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class SuggestRepositoryImpl implements SuggestRepository {
    private final ElasticsearchClient elasticsearchClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getSuggestList(String word) throws IOException {
        List<String> suggestList = new ArrayList<>();
        String SUGGEST_INDEX_NAME = PropertyUtil.getConstantValue("elasticsearch.suggest.index.name");
        String SUGGEST_TITLE = "search_suggest";
        String SUGGEST_FIELD = "suggest_ja";

        SearchRequest request = SearchRequest.of(s -> s
                .index(SUGGEST_INDEX_NAME)
                .suggest(su -> su
                        .suggesters(SUGGEST_TITLE, f -> f
                                .prefix(word)
                                .completion(co -> co
                                        .field(SUGGEST_FIELD)
                                        .skipDuplicates(true)
                                        .size(PropertyUtil.getConstantIntValueOrElseThrow("suggest.max.element"))))));
        ElasticsearchLogUtil.searchRequestLog(SUGGEST_INDEX_NAME, request);

        SearchResponse<Void> response = elasticsearchClient.search(request, Void.class);
        response.suggest().get(SUGGEST_TITLE).forEach(suggest ->
                suggest.completion().options().forEach(completion ->
                        suggestList.add(completion.text())));
        return suggestList;
    }
}
