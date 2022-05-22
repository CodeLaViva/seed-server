package com.nullpointer.seed.mappers;

import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lihongzheng
 * @date 2022/5/21
 * @description
 */
@Mapper(componentModel = "spring")
public interface SampleMapper {
    @Mapping(target = "description", ignore = true)
    Sample toDto(SampleEntity entity);

    @Mapping(target = "age", ignore = true)
    @Mapping(target = "birthday", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SampleEntity toEntity(Sample dto);
}
