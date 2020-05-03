package com.randomsturvs.collaboux.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;


@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String filepath = "bootstrap/kafkatopics.json";

    private HashMap<String, String> options = new HashMap<>();

    public String getBootstrapFilePath() {
        return this.filepath;
    }

    public void setBootstrapFilePath(String bootstrapFilePath) {
        this.filepath = bootstrapFilePath;
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, String> options) {
        this.options = options;
    }
}
