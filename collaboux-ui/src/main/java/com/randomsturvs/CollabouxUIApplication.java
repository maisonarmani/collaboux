package com.randomsturvs;

import com.randomsturvs.collaboux.properties.AppProperties;
import com.randomsturvs.collaboux.properties.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication()
@EnableJpaAuditing
@EnableAuthorizationServer
@EnableConfigurationProperties(value = {AppProperties.class, ConfigProperties.class})
public class CollabouxUIApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollabouxUIApplication.class, args);
    }
}
