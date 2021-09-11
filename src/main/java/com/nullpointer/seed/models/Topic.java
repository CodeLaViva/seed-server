package com.nullpointer.seed.models;

/**
 * @author lihongzheng
 * @date 2021/9/6
 * @description
 */
public class Topic {
    public String name;
    public int partition;
    public short replicationFactor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
