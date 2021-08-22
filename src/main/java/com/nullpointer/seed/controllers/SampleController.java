package com.nullpointer.seed.controllers;

import com.nullpointer.seed.configs.Setting;
import com.nullpointer.seed.models.Sample;
import com.nullpointer.seed.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description sample controller, follow by restful
 */
@RestController("/sample")
public class SampleController {

    private final Setting setting;
    private final SampleService service;

    @Value("${app.environment}")
    private String environment;

    @Autowired
    public SampleController(Setting setting, SampleService service) {
        this.setting = setting;
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Sample> get(@PathVariable int id) {
        Sample sample = service.get(id);
        // return ResponseEntity.ok(String.format("Request Id: %s, current environment: %s， %s", id, setting.getEnvironment(), environment));
        return ResponseEntity.ok(sample);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return id.isEmpty() ? ResponseEntity.ok(false) : ResponseEntity.ok(true);
    }

    @PostMapping()
    public ResponseEntity<Sample> save(@RequestParam Sample sample) {
        return ResponseEntity.ok(sample);
    }

    @PutMapping()
    public ResponseEntity<Sample> update(@RequestParam Sample sample) {
        return ResponseEntity.ok(sample);
    }

    @PatchMapping()
    public ResponseEntity<Sample> patch(@RequestParam Sample sample) {
        return ResponseEntity.ok(sample);
    }
}
