package com.nullpointer.seed.services.impls;

import com.nullpointer.seed.dto.request.SampleRequest;
import com.nullpointer.seed.dto.response.SampleResponse;
import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.mappers.SampleMapper;
import com.nullpointer.seed.repositories.SampleRepository;
import com.nullpointer.seed.services.SampleService;
import org.springframework.stereotype.Service;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
@Service
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleEntityRepository;
    private final SampleMapper sampleMapper;


    public SampleServiceImpl(SampleRepository sampleEntityRepository, SampleMapper sampleMapper) {
        this.sampleEntityRepository = sampleEntityRepository;
        this.sampleMapper = sampleMapper;
    }

    @Override
    public SampleResponse save(SampleRequest request) {
        SampleEntity sampleEntity = sampleMapper.toEntity(request);
        var savedEntity = sampleEntityRepository.save(sampleEntity);
        return sampleMapper.toResponse(savedEntity);
    }

    @Override
    public SampleResponse update(SampleRequest request) {
        return null;
    }

    @Override
    public SampleResponse patch(SampleRequest request) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public SampleResponse get(int id) {
        return sampleMapper.toResponse(sampleEntityRepository.findById(id).orElse(new SampleEntity()));
    }
}
