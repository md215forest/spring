package jp.co.practice.service.impl;

import jp.co.practice.dto.SampleDto;
import jp.co.practice.repository.SampleRepository;
import jp.co.practice.service.SampleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SampleDto> getById(Long id) {
        return sampleRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void input(SampleDto dto) {
        final Long id;
        if (dto.isInsert()) {
            // 登録
            id = sampleRepository.insert(dto);
        } else {
            // 更新
            id = sampleRepository.update(dto);
        }
        // idを使用した処理
    }

}
