package com.nullpointer.seed.common.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
    private ProducerConfig producer;
    private ConsumerConfig consumer;

    @Data
    public static class ProducerConfig {
        private int retries;
        private int batchSize;
        private int bufferMemory;
        private String keySerializer;
        private String valueSerializer;
        private String acks;
    }

    @Data
    public static class ConsumerConfig {
        private String groupId;
        private boolean enableAutoCommit;
        private int autoCommitInterval;
        private String keyDeserializer;
        private String valueDeserializer;
        private int fetchMaxWait;
        private int maxPollRecords;
    }
}
