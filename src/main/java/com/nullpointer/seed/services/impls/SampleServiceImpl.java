package com.nullpointer.seed.services.impls;

import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.mappers.SampleMapper;
import com.nullpointer.seed.models.Sample;
import com.nullpointer.seed.repositories.SampleEntityRepository;
import com.nullpointer.seed.services.SampleService;
import org.springframework.stereotype.Service;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
@Service
public class SampleServiceImpl implements SampleService {

    private final SampleEntityRepository sampleEntityRepository;
    private final SampleMapper sampleMapper;


    public SampleServiceImpl(SampleEntityRepository sampleEntityRepository, SampleMapper sampleMapper) {
        this.sampleEntityRepository = sampleEntityRepository;
        this.sampleMapper = sampleMapper;
    }

    @Override
    public Sample save(Sample sample) {
        SampleEntity sampleEntity = sampleMapper.toEntity(sample);
        var savedEntity = sampleEntityRepository.save(sampleEntity);
        return sampleMapper.toDto(savedEntity);
    }

    @Override
    public Sample update(Sample sample) {
        return null;
    }

    @Override
    public Sample patch(Sample sample) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Sample get(int id) {
        return sampleMapper.toDto(sampleEntityRepository.findById(id).orElse(new SampleEntity()));
    }
}
