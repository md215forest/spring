package jp.co.practice.repository.impl;

import jp.co.practice.dao.SampleDao;
import jp.co.practice.dto.SampleDto;
import jp.co.practice.entity.Sample;
import jp.co.practice.mapper.SampleMapper;
import jp.co.practice.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository {

    private final SampleDao dao;
    private final SampleMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SampleDto> getById(Long id) {
        return Optional.ofNullable(mapper.toDto(dao.selectById(id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long insert(SampleDto dto) {
        Sample entity = mapper.toEntity(dto);
        dao.insert(entity);
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long update(SampleDto dto) {
        Sample entity = mapper.toEntity(dto);
        dao.update(entity);
        return entity.getId();
    }
}
