package com.nullpointer.seed.controllers.clients;

import com.nullpointer.seed.dto.request.SampleRequest;
import com.nullpointer.seed.dto.response.SampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sample-service", url = "${sample.service.url:http://localhost:6666}")
public interface SampleClient {

    @GetMapping("/{id}")
    ResponseEntity<SampleResponse> getSample(@PathVariable("id") Integer id);

    @PostMapping()
    ResponseEntity<SampleResponse> createSample(@RequestBody SampleRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteSample(@PathVariable("id") Integer id);
}
