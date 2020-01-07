package com.randomsturvs.collaboux.dao;

import com.randomsturvs.collaboux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class OAuthUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserDetails(String username) {
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String userSQLQuery = "SELECT username,password FROM users WHERE username=?";
        List<User> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(rs.getString("password"));
                    return user;
                });


        if (list.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            list.get(0).setGrantedAuthorities(grantedAuthoritiesList);
            return list.get(0);
        }
        return null;
    }
}
