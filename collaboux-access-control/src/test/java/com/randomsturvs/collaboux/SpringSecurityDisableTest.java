package com.randomsturvs.collaboux;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CollabouxApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude= {SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class SpringSecurityDisableTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public  void testPasswordEncoder(){
        passwordEncoder.encode("Maison");
    }
}
