package com.randomsturvs.collaboux.config;

import com.randomsturvs.collaboux.provider.TokenProvider;
import com.randomsturvs.collaboux.services.CustomLocalUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.io.Serializable;
import java.util.LinkedList;

@Configuration
@EnableWebSocketMessageBroker

public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomLocalUserDetailsService customLocalUserDetailsService;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                // validate the authentication token per 10 request
                // reduces the load on the db server
                StompHeaderAccessor.wrap(message).getHost();
                final LinkedMultiValueMap<String, String> headers = (LinkedMultiValueMap<String, String>) message.getHeaders().get("nativeHeaders");
                if(headers != null && null != headers.get("access-token")){
                    Long userId = tokenProvider.getUserIdFromToken(((LinkedList<String>) headers.get("access-token")).getFirst());

                    UserDetails userDetails = customLocalUserDetailsService.loadUserById(userId);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new Serializable() {
                        @Override
                        public String toString() {
                            return super.toString();
                        }
                    });

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                return message;
            }
        });
    }
}