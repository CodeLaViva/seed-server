package com.nullpointer.seed.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author lihongzheng
 * @date 2021/9/6
 * @description
 */
@Component
public class KafkaConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 消费者要从头开始消费某个topic的全量数据，需要满足2个条件（spring-kafka）;
     * （1）使用一个全新的"group.id"（就是之前没有被任何消费者使用过）;
     * （2）指定"auto.offset.reset"参数的值为earliest;
     * @param content 消息内容
     */

    @KafkaListener(id = "client-1", topics = "kafka1", groupId = "client-group1")
    public void processMessage1(String content) {
        try {
            log.info("消费者kafka1-1监听消息,消息内容=[{}]", content);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(id = "client-2", topics = "kafka1", groupId = "client-group2")
    public void processMessage2(String content) {
        try {
            log.info("消费者kafka1-2监听消息,消息内容=[{}]", content);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @KafkaListener(id = "client-3", topics = "kafka2", groupId = "client-group3")
//    public void processMessage3(String content) {
//        log.info("消费者kafka2监听消息,消息内容=[{}]", content);
//    }

//    /**
//     * 批量消费
//     * containerFactory: 需要声明消费工厂名
//     * batchFactory： 在KafkaConfig中配置的消费者工厂类
//     *
//     * @param ack 消息确认对象
//     * @param records 消息内容
//     */
//    @KafkaListener(id = "client-4", topics = "kafka2", groupId = "group4",
//            containerFactory = "batchFactory")
//    public void processMessage(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
//        log.info("client-4 开始监听消息, Thread ID: {}， records size: {}", Thread.currentThread().getId(), records.size());
//        try {
//            for (ConsumerRecord<?, ?> record : records) {
//                Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//                if (kafkaMessage.isPresent()) {
//                    Object message = record.value();
//                    String topic = record.topic();
//                    long offset = record.offset();
//                    log.info("client-4监听消息,topic={}, offset={}, 消息内容=[{}]", topic, offset, message);
//                }
//            }
//            // 手动提交，设置offset
//            ack.acknowledge();
//        } catch (Exception e) {
//            log.error("client-4监听异常{}", e.getMessage(), e);
//        }
//    }
    /**
     * id是消费者监听容器
     * 配置topic和分区：监听两个topic，分别为topic1、topic2，topic1只接收分区0，3的消息，
     * topic2接收分区0和分区1的消息，但是分区1的消费者初始位置为5
     *
     * @param record 消费内容
     */
    @KafkaListener(id = "client-51", clientIdPrefix = "my",
            topicPartitions =
                    {@TopicPartition(topic = "kafka3", partitions = {"0", "1"}, partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "4") ),
                            @TopicPartition(topic = "kafka2", partitions = "0")
                    })
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("[client-5] 消息监听，topic={},key={},value={}", record.topic(), record.key(), record.value());
    }

    @KafkaListener(id = "client-6", topics = {"kafka1", "kafka2"})
    public void listen2(ConsumerRecord<?, ?> record) {
        log.info("[client-6] kafka1,kafka2 多主题消息监听，topic={},key={},value={}", record.topic(), record.key(), record.value());
    }
}