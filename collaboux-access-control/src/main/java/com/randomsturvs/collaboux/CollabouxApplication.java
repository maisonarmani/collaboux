package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.social.models.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication()
@EnableJpaAuditing
@EnableAuthorizationServer
@EnableConfigurationProperties(AppProperties.class)
public class CollabouxApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollabouxApplication.class, args);
	}
}
