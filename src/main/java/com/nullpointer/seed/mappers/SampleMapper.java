package com.nullpointer.seed.mappers;

import com.nullpointer.seed.dto.request.SampleRequest;
import com.nullpointer.seed.dto.response.SampleResponse;
import com.nullpointer.seed.entities.SampleEntity;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lihongzheng
 * @date 2022/5/21
 * @description
 */
@Mapper(componentModel = "spring")
public interface SampleMapper {
    SampleResponse toResponse(SampleEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SampleEntity toEntity(SampleRequest request);
}
