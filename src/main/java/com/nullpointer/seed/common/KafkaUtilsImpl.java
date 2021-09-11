package com.nullpointer.seed.common;

import javafx.util.Pair;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.*;

/**
 * @author lihongzheng
 * @date 2021/8/24
 * @description
 */
@Service
public class KafkaUtilsImpl implements KafkaUtils {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaAdmin kafkaAdmin;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public KafkaUtilsImpl(KafkaAdmin kafkaAdmin, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaAdmin = kafkaAdmin;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private int timeout = 6000;

    /**
     * 发送数据到指定的topic中
     *
     * @param topicName topic名称
     * @param msg       数据
     * @return 发送的状态
     */
    @Override
    public Boolean sendDataToTopic(String topicName, String msg) {
        try {
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicName, msg);
            result.get();
            return !result.completable().isCompletedExceptionally();
        } catch (Exception e) {
            log.info("Send message failed, topic={}, msg={}, failure Message={}", topicName, msg, e.getMessage());
            return false;
        }
    }

    /**
     * 发送数据到指定的topic和key中
     *
     * @param topicName topic名称
     * @param key       key
     * @param msg       消息
     * @return 发送状态
     */
    @Override
    public ListenableFuture<SendResult<String, String>> sendDataToTopicAndKey(String topicName, String key, String msg) {
        return kafkaTemplate.send(topicName, key, msg);
    }

    /**
     * 发送数据到指定的topic的中
     *
     * @param topic     topic名称
     * @param partition 分区名称
     * @param key       指定的key
     * @param msg       消息
     * @return 发送状态
     */
    @Override
    public ListenableFuture<SendResult<String, String>> sendDataToTopicAppointPartition(String topic, Integer partition, String key, String msg) {
        return kafkaTemplate.send(topic, partition, key, msg);
    }

    /**
     * 校验topic是否已经存在于kafka中
     *
     * @param topicName topic的名称
     * @return 是否存在的状态
     */
    @Override
    public Boolean isExistTopic(String topicName) {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
            listTopicsOptions.listInternal(true);
            ListTopicsResult listTopicsResult = adminClient.listTopics(listTopicsOptions);
            return listTopicsResult.names().get().contains("topicName");
        } catch (Exception e) {
            log.info("Verify topic: {} 是否已经存在于kafka中异常 {}", topicName, e.getMessage());
            return false;
        }
    }

    /**
     * 创建指定的topic
     *
     * @param topicName         topic的名称
     * @param topicPartition    话题创建的分区
     * @param replicationFactor 话题创建的副本， 不能大于broker的数量
     * @return 是否创建成功
     */
    @Override
    public Boolean createTopic(String topicName, Integer topicPartition, short replicationFactor) {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            Boolean existTopic = isExistTopic(topicName);
            if (existTopic) {
                return true;
            }
            NewTopic newTopic = new NewTopic(topicName, topicPartition, replicationFactor);
            List<NewTopic> newTopics = Collections.singletonList(newTopic);
            adminClient.createTopics(newTopics);
            return isExistTopic(topicName);
        } catch (Exception e) {
            log.error("创建话题{}失败, Cause by: {}", topicName, e.getMessage());
            return false;
        }
    }

    /**
     * 删除指定topic(如果broker那没有设置允许删除topic的话，此调用会持续等待最终超时返回)
     *
     * @param topicNames 待删除的topic
     * @return 删除是否成功
     */
    @Override
    public List<Pair<String, Boolean>> deleteTopic(String[] topicNames) {
        List<Pair<String, Boolean>> result = new ArrayList<>();
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            DeleteTopicsOptions options = new DeleteTopicsOptions();
            options.timeoutMs(timeout);
            DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(topicNames), options);
            for (Map.Entry<String, KafkaFuture<Void>> e : deleteTopicsResult.values().entrySet()) {
                String topic = e.getKey();
                KafkaFuture<Void> future = e.getValue();
                future.get();
                result.add(new Pair<>(topic, !future.isCompletedExceptionally()));
            }
            return result;
        } catch (Exception e) {
            log.error("删除话题{}失败, Cause by: {}", String.join(",", topicNames), e.getMessage());
            return result;
        }
    }

    /**
     * 获取所有的topic
     *
     * @return topic集合
     */
    @Override
    public Map<String, TopicListing> getTopics() {
        ListTopicsOptions options = new ListTopicsOptions();
        //设置超时时间
        options.timeoutMs(timeout);
        //不列出kafka内部topic
        options.listInternal(false);
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            ListTopicsResult listTopicsResult = adminClient.listTopics(options);
            return listTopicsResult.namesToListings().get();
        } catch (Exception e) {
            log.error("Query topic failed, reason: {}", e.getMessage());
            return null;
        }
    }
}
