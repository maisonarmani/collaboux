package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.properties.ConfigProperties;
import com.randomsturvs.collaboux.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties({AppProperties.class, ConfigProperties.class})
public class AccessControlUIApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccessControlUIApplication.class, args);
    }
}
