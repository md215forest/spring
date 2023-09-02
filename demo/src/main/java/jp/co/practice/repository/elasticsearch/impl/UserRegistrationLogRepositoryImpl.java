package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import jp.co.practice.dto.elasticsearch.UserRegistrationLogRequestDto;
import jp.co.practice.mapper.elasticsearch.ElasticsearchRequestMapper;
import jp.co.practice.repository.elasticsearch.UserRegistrationLogRepository;
import jp.co.practice.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class UserRegistrationLogRepositoryImpl implements UserRegistrationLogRepository {
    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchRequestMapper elasticsearchRequestMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(UserRegistrationLogRequestDto dto) throws IOException {
        String INDEX_NAME = PropertyUtil.getConstantValue("elasticsearch.user.registration.log");
        IndexRequest<Object> request = IndexRequest.of(i -> i
                .index(INDEX_NAME)
                .document(elasticsearchRequestMapper.toRequest(dto)));
        ElasticsearchLogUtil.indexRequestLog(INDEX_NAME, request);

        elasticsearchClient.index(request);
    }
}
