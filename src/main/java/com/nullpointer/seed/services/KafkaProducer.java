package com.nullpointer.seed.services;

import com.alibaba.fastjson.JSON;
import com.nullpointer.seed.common.KafkaUtils;
import com.nullpointer.seed.models.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author lihongzheng
 * @date 2021/9/6
 * @description
 */
@Component
public class KafkaProducer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final KafkaUtils kafkaUtils;

    public KafkaProducer(KafkaUtils kafkaUtils) {
        this.kafkaUtils = kafkaUtils;
    }

    public void send(String topic, String key, KafkaMessage kafkaMessage) {
        String msg = JSON.toJSONString(kafkaMessage);
        kafkaUtils.sendDataToTopicAndKey(topic, key, msg).addCallback(success -> {
            // 消息发送到的topic
            String successTopic = Objects.requireNonNull(success).getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            log.info("发送普通消息, topic={},key={},msg={}", topic, key, msg);
            log.info("successTopic={},partition={},offset={}", successTopic, partition, offset);
        }, failure -> {
            log.info("发送普通消息失败, topic={},key={},msg={}, failure Message={}", topic, key, msg, failure.getMessage());
        });
    }

    public boolean send(String topic, Integer partition, String key, KafkaMessage kafkaMessage) {
        // 或者 JSON.toJSONString(kafkaMessage, SerializerFeature.WriteDateUseDateFormat);
        String msg = JSON.toJSONStringWithDateFormat(kafkaMessage, "yyyy-MM-dd HH:mm:ss");
        try {
            ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaUtils.sendDataToTopicAppointPartition(topic, partition, key, msg);
            log.info("发送普通消息，topic={},key={},msg={}", topic, key, msg);
            CompletableFuture<SendResult<String, String>> completable = sendResultListenableFuture.completable();
            completable.get();
            return !completable.isCompletedExceptionally();
        } catch (Exception e) {
            log.error("发送普通消息失败，topic={},key={},msg={}", topic, key, msg);
            return false;
        }
    }

    public void send(String topic, Integer partition, String key, String kafkaMessage) {
        kafkaUtils.sendDataToTopicAppointPartition(topic, partition, key, kafkaMessage);
        log.info("发送普通消息，topic={},key={},msg={}", topic, key, kafkaMessage);
    }
}
