package jp.co.practice.repository.elasticsearch;

import jp.co.practice.dto.elasticsearch.UserInformationRequestDto;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ユーザ情報 Repository
 */
public interface UserInformationRepository {

    /**
     * ユーザ情報の登録
     * @param dto ユーザ情報リクエストDTO
     */
    String insert(UserInformationRequestDto dto) throws IOException;

    /**
     * ユーザ情報の更新
     * @param elasticsearchKey Elasticsearch _id
     * @param licenseId ライセンス番号
     * @param dateTime 更新日
     */
    void update(String elasticsearchKey, String licenseId, LocalDateTime dateTime) throws IOException;
}
