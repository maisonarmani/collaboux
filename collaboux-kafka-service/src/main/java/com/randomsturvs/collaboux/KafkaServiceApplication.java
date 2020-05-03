package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.properties.KafkaProperties;
import com.randomsturvs.collaboux.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication()
@EnableJpaAuditing
@EnableAuthorizationServer
@EnableConfigurationProperties(value = {KafkaProperties.class,AppProperties.class})
public class KafkaServiceApplication {
    public static void main(String[] args) {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        SpringApplication.run(KafkaServiceApplication.class, args);
    }
}
