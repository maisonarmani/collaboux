package com.randomsturvs.collaboux.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import redis.clients.jedis.JedisShardInfo;

import javax.sql.DataSource;

@Configuration
public class CollabouxConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5435/collaboux");
        dataSourceBuilder.username("collaboux");
        dataSourceBuilder.password("collaboux");
        return dataSourceBuilder.build();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost",6570,false);
        return new JedisConnectionFactory(jedisShardInfo);
    }
}
