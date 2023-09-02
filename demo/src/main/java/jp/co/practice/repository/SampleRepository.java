package jp.co.practice.repository;

import jp.co.practice.dto.SampleDto;

import java.util.Optional;

public interface SampleRepository {

    /**
     * 管理IDから取得する
     * @param id 管理ID
     * @return サンプルDTO
     */
    Optional<SampleDto> getById(Long id);

    /**
     * Sampleテーブルにデータを登録する
     * @param dto サンプルDTO
     * @return 登録したデータの管理ID
     */
    Long insert(SampleDto dto);

    /**
     * Sampleテーブルのデータを更新する
     * @param dto サンプルDTO
     * @return 更新したデータの管理ID
     */
    Long update(SampleDto dto);
}
