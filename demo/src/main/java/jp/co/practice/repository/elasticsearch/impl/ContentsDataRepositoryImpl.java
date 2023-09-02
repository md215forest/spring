package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.ScriptLanguage;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.json.JsonData;
import jp.co.practice.repository.elasticsearch.ContentsDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class ContentsDataRepositoryImpl implements ContentsDataRepository {

    private final ElasticsearchAsyncClient elasticsearchAsyncClient;

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public void countPvTotal(String indexName, String elasticsearchKey) {
        val request = UpdateRequest.of(u -> u
                .index(indexName)
                .id(elasticsearchKey)
                .script(s -> s
                        .inline(i -> i
                                .source("ctx._source.pv_total += params.count")
                                .lang(ScriptLanguage.Painless)
                                .params("count", JsonData.of(1)))));
        ElasticsearchLogUtil.updateRequestLog(indexName, request);

        elasticsearchAsyncClient.update(request, Void.class);
    }
}
