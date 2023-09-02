package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import jp.co.practice.dto.elasticsearch.ContentsAccessLogRequestDto;
import jp.co.practice.mapper.elasticsearch.ElasticsearchRequestMapper;
import jp.co.practice.repository.elasticsearch.ContentsAccessLogRepository;
import jp.co.practice.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class ContentsAccessLogRepositoryImpl implements ContentsAccessLogRepository {
    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchRequestMapper elasticsearchRequestMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(ContentsAccessLogRequestDto dto) throws IOException {
        String INDEX_NAME = PropertyUtil.getConstantValue("elasticsearch.contents.access.log");
        IndexRequest<Object> request = IndexRequest.of(i -> i
                .index(INDEX_NAME)
                .document(elasticsearchRequestMapper.toRequest(dto)));
        ElasticsearchLogUtil.indexRequestLog(INDEX_NAME, request);

        elasticsearchClient.index(request);
    }
}
