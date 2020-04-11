package com.randomsturvs.collaboux.models;


import net.minidev.json.JSONObject;

public class KafkaTopic {
    private String topic;
    private Integer partition,replicationFactor;
    private String readOffset;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public String getReadOffset() {
        return readOffset;
    }

    public void setReadOffset(String readOffset) {
        this.readOffset = readOffset;
    }

    public static KafkaTopic fromJSON(JSONObject topic){
        KafkaTopic kafkaTopic = new KafkaTopic();
        kafkaTopic.setPartition(Integer.valueOf(topic.getAsString("partition")));
        kafkaTopic.setReplicationFactor(Integer.valueOf(topic.getAsString("replicationFactor")));
        kafkaTopic.setReadOffset(topic.getAsString("readOffset"));
        kafkaTopic.setTopic(topic.getAsString("name"));
        return kafkaTopic;
    }


    public void setReplicationFactor(Integer replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public Integer getReplicationFactor() {
        return this.replicationFactor;
    }
}
