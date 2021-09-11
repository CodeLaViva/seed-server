package com.nullpointer.seed.services;

import com.nullpointer.seed.models.KafkaMessage;
import com.nullpointer.seed.models.Topic;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * @author lihongzheng
 * @date 2021/8/25
 * @description
 */
public interface KafkaService {
    /**
     * 1
     * @param topic topic name
     * @return true
     */
    boolean createTopic(Topic topic);

    /**
     * 1
     * @param topicName topic name
     * @return true
     */
    boolean deleteTopic(String topicName);

    /**
     * get all topic name
     * @return topic name list
     */
    List<String> getTopics();

    boolean isTopicExist(String topicName);

    boolean sendDataToTopic(KafkaMessage message);

    ListenableFuture<SendResult<String, String>> sendDataToTopicAndKey(String topicName, String key, String msg);

    ListenableFuture<SendResult<String, String>> sendDataToTopicAppointPartition(String topicName, int partition, String key, String msg);

    void producerSend(KafkaMessage message);

    boolean producerSendBoolean(KafkaMessage message);
}
