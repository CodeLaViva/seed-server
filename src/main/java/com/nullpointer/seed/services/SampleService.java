package com.nullpointer.seed.services;

import com.nullpointer.seed.dto.request.SampleRequest;
import com.nullpointer.seed.dto.response.SampleResponse;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
public interface SampleService {

    SampleResponse save(SampleRequest request);

    SampleResponse update(SampleRequest request);

    SampleResponse patch(SampleRequest request);

    Boolean delete(int id);

    SampleResponse get(int id);
}
