package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.config.ConfigProperties;
import com.randomsturvs.collaboux.social.models.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.Properties;

@SpringBootApplication()
@EnableJpaAuditing
@EnableAuthorizationServer
@PropertySources({@PropertySource("classpath:application.properties")})
@EnableConfigurationProperties({AppProperties.class, ConfigProperties.class})
public class CollabouxApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollabouxApplication.class, args);
	}
}
