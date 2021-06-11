package com.nullpointer.seed.controllers;

import com.nullpointer.seed.models.Sample;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description sample controller, follow by restful
 */
@RestController("/sample")
public class SampleController {

    public SampleController() {}

    @GetMapping("{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return ResponseEntity.ok(String.format("Request Id is %s", id));
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
