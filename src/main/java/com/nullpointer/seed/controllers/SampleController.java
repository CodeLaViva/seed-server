package com.nullpointer.seed.controllers;

import com.nullpointer.seed.annotation.MonitorPerformance;
import com.nullpointer.seed.clients.SampleClient;
import com.nullpointer.seed.dto.request.SampleRequest;
import com.nullpointer.seed.dto.response.SampleResponse;
import com.nullpointer.seed.services.SampleService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description sample controller, follow by restful
 */
@RestController
@RequestMapping("/sample")
@Slf4j
@AllArgsConstructor
public class SampleController implements SampleClient {

    private final SampleService service;

    @MonitorPerformance
    public ResponseEntity<SampleResponse> getSample(@PathVariable Integer id) {
        log.info("获取样本信息，id: {}", id);
        SampleResponse response = service.get(id);
        return ResponseEntity.ok(response);
    }

    @MonitorPerformance
    public ResponseEntity<SampleResponse> createSample(@Valid @RequestBody SampleRequest request) {
        log.info("创建新样本: {}", request);
        SampleResponse response = service.save(request);
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<Boolean> deleteSample(@PathVariable("id") Integer id) {
        var response = service.delete(id);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<SampleResponse> update(@RequestParam SampleRequest sample) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<SampleResponse> patch(@RequestParam SampleRequest sample) {
        return ResponseEntity.ok(null);
    }
}
