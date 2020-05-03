package com.randomsturvs.collaboux.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


@Controller
public class WebsocketTest {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public Greeting greeting(HelloMessage message) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        Thread.sleep(1000);
        simpMessagingTemplate.convertAndSend("/topic/greetings", new Greeting("Hello, World !"));
        return new Greeting("Hello, World !");
    }


    private static class Greeting {
        private String content;

        public Greeting() { }

        public Greeting(String content) { this.content = content; }

        public String getContent() { return content; }
    }

    private static class HelloMessage {

        private String name;

        public HelloMessage() {}

        public HelloMessage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
