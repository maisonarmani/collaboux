package com.randomsturvs.excollabo.controller;

import com.randomsturvs.excollabo.validator.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.security.Principal;

@RestController()
public class SecuredController  implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.printf("Something about this guy ! %s", beanFactory.getAliases("SecuredController").toString());
    }

    @GetMapping(value = "/ping-pong")
    public String sayHello(Principal principal, @RequestParam("ping") String ping){
        return ping;
    }


    @RequestMapping(value = "/hello", consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public String sayHello(Principal principal, @Validated User user ){
        return user.getPhoneNumber();
    }

    @RequestMapping(value = "/public/ping-pong", consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public String sayPing(Principal principal,
                          @RequestBody @Validated User user ){
        return user.getPhoneNumber();
    }
}




