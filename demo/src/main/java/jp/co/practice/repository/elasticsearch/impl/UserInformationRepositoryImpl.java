package jp.co.practice.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ScriptLanguage;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.json.JsonData;
import jp.co.practice.constant.DateTimeConstant;
import jp.co.practice.dto.elasticsearch.UserInformationRequestDto;
import jp.co.practice.mapper.elasticsearch.ElasticsearchRequestMapper;
import jp.co.practice.repository.elasticsearch.UserInformationRepository;
import jp.co.practice.util.DateTimeUtil;
import jp.co.practice.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class UserInformationRepositoryImpl implements UserInformationRepository {
    private final static String USER_INFORMATION_INDEX_NAME = PropertyUtil.getConstantValue("elasticsearch.user.information.index.name");
    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchRequestMapper elasticsearchRequestMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public String insert(UserInformationRequestDto dto) throws IOException {
        IndexRequest<Object> request = IndexRequest.of(i -> i
                .index(USER_INFORMATION_INDEX_NAME)
                .document(elasticsearchRequestMapper.toRequest(dto)));
        ElasticsearchLogUtil.indexRequestLog(USER_INFORMATION_INDEX_NAME, request);

        IndexResponse response = elasticsearchClient.index(request);
        return response.id();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String elasticsearchKey, String licenseId, LocalDateTime dateTime) throws IOException {
        Map<String, JsonData> scriptMap = new HashMap<>();
        scriptMap.put("count", JsonData.of(1));
        scriptMap.put("time", JsonData.of(DateTimeUtil.formatLocalDateTime(dateTime, DateTimeConstant.SYSTEM_PROPERTY_DATE_TIME_HYPHEN)));
        scriptMap.put("license", JsonData.of(licenseId));

        val request = UpdateRequest.of(u -> u
                .index(USER_INFORMATION_INDEX_NAME)
                .id(elasticsearchKey)
                .script(s -> s
                        .inline(i -> i
                                .source("ctx._source.total_session += params.count;ctx._source.last_access_time = params.time;ctx._source.license = params.license")
                                .lang(ScriptLanguage.Painless)
                                .params(scriptMap))));
        ElasticsearchLogUtil.updateRequestLog(USER_INFORMATION_INDEX_NAME, request);

        elasticsearchClient.update(request, Void.class);
    }
}
