package jp.co.practice.mapper;

import jp.co.practice.dto.SampleDto;
import jp.co.practice.entity.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SampleMapper {

    /**
     * {@link Sample} -> {@link SampleDto}
     */
    SampleDto toDto(Sample entity);

    /**
     * {@link SampleDto} -> {@link Sample}
     */
    Sample toEntity(SampleDto dto);
}
