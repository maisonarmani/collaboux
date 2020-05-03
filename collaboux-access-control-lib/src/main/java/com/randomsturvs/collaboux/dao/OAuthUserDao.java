package com.randomsturvs.collaboux.dao;

import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
public class OAuthUserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User getUserDetails(String username) {

        User user = new User();
        user.setUsername(username);

        Optional<User> optionalUser = userRepository.findOne(Example.of(user));
        if(optionalUser.isPresent()){
            user =  optionalUser.get();
            userRoleRepository.findAllByUser(user);
        }

        return null;
    }
}
