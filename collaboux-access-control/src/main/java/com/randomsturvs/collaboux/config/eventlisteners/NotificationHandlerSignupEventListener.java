package com.randomsturvs.collaboux.config.eventlisteners;

import com.randomsturvs.collaboux.events.UserSignupEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandlerSignupEventListener  implements  ApplicationListener<UserSignupEvent>{

    @Override
    public void onApplicationEvent(UserSignupEvent userSignupEvent) {
        // push message to kafka for event for notification to be sent
    }
}

