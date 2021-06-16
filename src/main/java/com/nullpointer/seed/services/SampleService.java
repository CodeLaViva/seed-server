package com.nullpointer.seed.services;

import com.nullpointer.seed.models.Sample;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
public interface SampleService {

    Sample save(Sample sample);

    Sample update(Sample sample);

    Sample patch(Sample sample);

    Boolean delete(String id);

    Sample get(String id);
}
