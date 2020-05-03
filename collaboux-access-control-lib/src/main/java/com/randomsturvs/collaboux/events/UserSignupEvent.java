package com.randomsturvs.collaboux.events;

import com.randomsturvs.collaboux.entity.User;
import org.springframework.context.ApplicationEvent;

public class UserSignupEvent extends ApplicationEvent {
    private User user;

    public UserSignupEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

