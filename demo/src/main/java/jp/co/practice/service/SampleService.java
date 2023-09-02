package jp.co.practice.service;

import jp.co.practice.dto.SampleDto;

import java.util.Optional;

public interface SampleService {

    /**
     * 管理IDから取得する
     * @param id 管理ID
     * @return サンプルDTO
     */
    Optional<SampleDto> getById(Long id);

    /**
     * 登録/更新処理を行う
     * @param dto サンプルDTO
     */
    void input(SampleDto dto);
}
