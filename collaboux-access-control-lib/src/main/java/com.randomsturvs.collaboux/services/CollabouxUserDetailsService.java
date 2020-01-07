package com.randomsturvs.collaboux.services;

import com.randomsturvs.collaboux.dao.OAuthUserDao;
import com.randomsturvs.collaboux.entity.CollabouxUser;
import com.randomsturvs.collaboux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CollabouxUserDetailsService implements UserDetailsService {
    @Autowired
    OAuthUserDao oauthDao;

    @Override
    public CollabouxUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = oauthDao.getUserDetails(username);
            CollabouxUser customUser = new CollabouxUser(user);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("user " + username + " was not found in the database");
        }
    }
}