package com.nullpointer.seed.services.impls;

import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.models.Sample;
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

    private final SampleRepository repository;

    public SampleServiceImpl(SampleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sample save(Sample sample) {
        return null;
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
        Sample sample = new Sample();
        SampleEntity entity =  repository.getOne(id);
        sample.setId(entity.getId());
        sample.setName(entity.getName());
        sample.setDescription(entity.getDescription());
        return sample;
    }
}
