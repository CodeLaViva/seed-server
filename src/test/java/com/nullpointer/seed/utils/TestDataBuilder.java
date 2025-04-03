package com.nullpointer.seed.utils;

import com.nullpointer.seed.dto.request.SampleRequest;

public class TestDataBuilder {

    public static SampleRequest createSample() {
        return SampleRequest.builder()
            .id(1)
            .name("Test")
            .description("Test Description")
            .build();
    }
}
