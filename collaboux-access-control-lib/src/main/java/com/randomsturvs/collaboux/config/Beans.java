package com.randomsturvs.collaboux.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;

@Configuration
@DependsOn({"entityManagerFactory"})
public class Beans {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Primary
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
        factory.openSession();
        return factory;
    }
}
