package com.nullpointer.seed.models;

import java.util.Date;

/**
 * @author lihongzheng
 * @date 2021/9/6
 * @description
 */
public class KafkaMessage {
    private String message;
    private String topicName;
    private String key;
    private int topicPartition;
    private Date dateTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicPartition() {
        return topicPartition;
    }

    public void setTopicPartition(int topicPartition) {
        this.topicPartition = topicPartition;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
