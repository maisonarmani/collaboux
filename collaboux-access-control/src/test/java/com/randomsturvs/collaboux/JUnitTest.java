package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.encoder.CollabouxPasswordEncoder;
import com.randomsturvs.collaboux.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ValidationException;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class JUnitTest {

    @Mock
    CollabouxPasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(JUnitTest.class);

    @Before
    public void init(){
        Mockito.when(passwordEncoder.encode(any())).thenReturn("Maison");
    }

    @Test
    public void testMockedPasswordEncoder(){
        logger.info(passwordEncoder.encode("P"));
    }

    @Test(expected = ValidationException.class)
    public void testValidator(){

        User user = new User();
        user.setPhoneNumber("08167267326");
        user.setUserName("Maison Armani");



    }

}
