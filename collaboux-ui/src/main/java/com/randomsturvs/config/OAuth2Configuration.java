package com.randomsturvs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import redis.clients.jedis.JedisShardInfo;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Order(1)
public class OAuth2Configuration extends ResourceServerConfigurerAdapter {

    @Value("${collaboux.resource.id:collaboux-ui}")
    private String resourceId;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId)
                .authenticationManager(new OAuth2AuthenticationManager())
                .tokenStore(getTokenStore());
    }

    public TokenStore getTokenStore(){
        return new RedisTokenStore(jedisConnectionFactory());
    }


    @Bean
    @Primary
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost",6570,false);
        return new JedisConnectionFactory(jedisShardInfo);
    }


}
