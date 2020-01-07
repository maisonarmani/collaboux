package com.randomsturvs.collaboux.encoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Primary
public class CollabouxPasswordEncoder extends BCryptPasswordEncoder implements PasswordEncoder{

}