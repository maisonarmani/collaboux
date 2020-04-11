package com.randomsturvs.collaboux.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bootstrap")
public class KafkaProperties {
    private String fIlePath = "bootstrap/kafkatopics.json";

    public String getBootstrapFIlePath() {
        return this.fIlePath;
    }

    public void setBootstrapFIlePath(String bootstrapFIlePath) {
        this.fIlePath = bootstrapFIlePath;
    }
}
