package com.nullpointer.seed.common;

import javafx.util.Pair;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;

/**
 * @author lihongzheng
 * @date 2021/8/24
 * @description
 */
public interface KafkaUtils {
    /**
     * 发送数据到指定的topic中
     *
     * @param topicName topic名称
     * @param msg      数据
     * @return 发送的状态
     */
    Boolean sendDataToTopic(String topicName, String msg);

    /**
     * 发送数据到指定的topic和key中
     *
     * @param topicName topic名称
     * @param key       key
     * @param msg       消息
     * @return 发送状态
     */
    ListenableFuture<SendResult<String, String>> sendDataToTopicAndKey(String topicName, String key, String msg);

    /**
     * 发送数据到指定的topic的中
     *
     * @param topic     topic名称
     * @param partition 分区名称
     * @param key       指定的key
     * @param msg       消息
     * @return 发送状态
     */
    ListenableFuture<SendResult<String, String>> sendDataToTopicAppointPartition(String topic, Integer partition, String key, String msg);

    /**
     * 校验topic是否已经存在于kafka中
     *
     * @param topicName topic的名称
     * @return 是否存在的状态
     */
    Boolean isExistTopic(String topicName);

    /**
     * 创建指定的topic
     *
     * @param topicName         topic的名称
     * @param topicPartition    话题创建的分区
     * @param replicationFactor 话题创建的副本， 不能大于broker的数量
     * @return 是否创建成功
     */
    Boolean createTopic(String topicName, Integer topicPartition, short replicationFactor);

    /**
     * 删除话题
     * @param topicNames 话题名称
     * @return 删除结果
     */
    List<Pair<String, Boolean>> deleteTopic(String[] topicNames);

    /**
     * 获取所有的topic
     * @return topic集合
     */
    Map<String, TopicListing> getTopics();
}
