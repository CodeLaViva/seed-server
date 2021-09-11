package com.nullpointer.seed.services.impls;

import com.nullpointer.seed.common.KafkaUtils;
import com.nullpointer.seed.models.KafkaMessage;
import com.nullpointer.seed.models.Topic;
import com.nullpointer.seed.services.KafkaProducer;
import com.nullpointer.seed.services.KafkaService;
import javafx.util.Pair;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lihongzheng
 * @date 2021/8/25
 * @description
 */
@Service
public class KafkaServiceImpl implements KafkaService {
    private final KafkaUtils kafkaUtils;
    private KafkaProducer producer;

    public KafkaServiceImpl(KafkaUtils kafkaUtils) {
        this.kafkaUtils = kafkaUtils;
        producer = new KafkaProducer(kafkaUtils);
    }

    @Override
    public boolean createTopic(Topic topic) {
        return kafkaUtils.createTopic(topic.getName(), topic.getPartition(), topic.getReplicationFactor());
    }

    @Override
    public boolean deleteTopic(String topicName) {
        List<Pair<String, Boolean>> result = kafkaUtils.deleteTopic(new String[]{topicName});
        return !result.isEmpty() && result.get(0).getValue();
    }

    @Override
    public List<String> getTopics() {
        return new ArrayList<>(kafkaUtils.getTopics().keySet());
    }

    @Override
    public boolean isTopicExist(String topicName) {
        return kafkaUtils.isExistTopic(topicName);
    }

    @Override
    public boolean sendDataToTopic(KafkaMessage message) {
        return kafkaUtils.sendDataToTopic(message.getTopicName(), message.getMessage());
    }

    @Override
    public ListenableFuture<SendResult<String, String>> sendDataToTopicAndKey(String topicName, String key, String msg) {
        return kafkaUtils.sendDataToTopicAndKey(topicName, key, msg);
    }

    @Override
    public ListenableFuture<SendResult<String, String>> sendDataToTopicAppointPartition(String topicName, int partition, String key, String msg) {
        return kafkaUtils.sendDataToTopicAppointPartition(topicName, partition, key, msg);
    }

    @Override
    public void producerSend(KafkaMessage message) {
        message.setDateTime(Date.from(Instant.now()));
        producer.send(message.getTopicName(), message.getKey(), message);
    }

    @Override
    public boolean producerSendBoolean(KafkaMessage message) {
        message.setDateTime(Date.from(Instant.now()));
        return producer.send(message.getTopicName(), message.getTopicPartition(), message.getKey(), message);
    }
}
