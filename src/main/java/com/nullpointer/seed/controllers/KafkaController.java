package com.nullpointer.seed.controllers;

import com.nullpointer.seed.models.KafkaMessage;
import com.nullpointer.seed.models.Topic;
import com.nullpointer.seed.services.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lihongzheng
 * @date 2021/8/25
 * @description
 */
@RestController
@RequestMapping("/messages")
public class KafkaController {
    /**
     * 1. Create topic
     * 2. Delete topic
     * 3. Update topic
     * 4. Get topics
     * 5. Send a message to the specified topic
     */

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createTopics(@RequestBody Topic topic) {
        return ResponseEntity.ok(kafkaService.createTopic(topic));
    }

    @DeleteMapping("{topicName}")
    public ResponseEntity<Boolean> deleteTopic(@PathVariable String topicName) {
        return ResponseEntity.ok(kafkaService.deleteTopic(topicName));
    }

    @GetMapping
    public ResponseEntity<List<String>> getTopics() {
        return ResponseEntity.ok(kafkaService.getTopics());
    }

    @GetMapping("{topicName}")
    public ResponseEntity<Boolean> isExist(@PathVariable String topicName) {
        return ResponseEntity.ok(kafkaService.isTopicExist(topicName));
    }

    @PostMapping("send")
    public ResponseEntity<Boolean> send(@RequestBody KafkaMessage message) {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            flag = kafkaService.producerSendBoolean(message);
        }
        kafkaService.producerSend(message);
        return ResponseEntity.ok(flag);
    }

    @PostMapping("sendPartition")
    public ResponseEntity<Boolean> sendPartition(@RequestBody KafkaMessage message) {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            flag = kafkaService.producerSendBoolean(message);
        }
        return ResponseEntity.ok(flag);
    }


}
