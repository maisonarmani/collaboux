package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.config.AccessControl;
import com.randomsturvs.collaboux.entity.Authority;
import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.RoleAuthority;
import com.randomsturvs.collaboux.enums.Domain;
import com.randomsturvs.collaboux.repository.RoleRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@ContextConfiguration
@SpringBootTest(classes = CollabouxApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude= {SecurityAutoConfiguration.class })
public class SpringSecurityDisableTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionFactory sessionFactory;



    Logger logger = LoggerFactory.getLogger(SpringSecurityDisableTest.class);

    @Test
    public void testPasswordEncoder(){
        passwordEncoder.encode("Maison");
    }


    @Test
    public void testRepository(){
        CriteriaBuilder cb =  sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root.get("name")).where(cb.equal(root.get("id"), 1));
        sessionFactory.openSession().createQuery(criteriaQuery).getResultList();
    }
    @Test
    public void testJSON(){
        JSONParser jsonParser = new JSONParser(0);

        URL resource = getClass().getClassLoader().getResource("bootstrap/access-control.json");

        if (resource != null){
            try (FileReader reader = new FileReader(resource.getFile()))
            {
                Object obj = jsonParser.parse(reader);
                JSONArray accessControls = (JSONArray) obj;
                AccessControl accessControl = new AccessControl();
                accessControls.forEach((json)->{
                    JSONObject jsonObject = (JSONObject) json;
                    accessControl.setName(jsonObject.getAsString("name"));
                    accessControl.setUpdateMode(AccessControl.UpdateMode.valueOf(jsonObject.getAsString("updateMode")));

                    JSONArray authorities = (JSONArray) jsonObject.get("authorities");
                    JSONArray roles = (JSONArray) jsonObject.get("roles");
                    JSONObject rolesAuthorities = (JSONObject) jsonObject.get("rolesAuthorities");


                    if (authorities != null){
                        Set<Authority> authoritySet = new HashSet<>();

                        authorities.forEach((b)->{
                            JSONObject object = (JSONObject) b;
                            Authority authority = new Authority(object.getAsString("name"));
                            authority.setDomain(Domain.valueOf(object.getAsString("domain")));
                            authority.setFriendlyName(object.getAsString("friendlyName"));
                            authority.setDescription(object.getAsString("description"));
                            authoritySet.add(authority);
                        });

                        accessControl.setAuthorities(authoritySet);
                    }else{
                        logger.warn("No authority was added for your service");
                    }


                    if(roles != null){

                        Set<Role> rolesSet = new HashSet<>();

                        roles.forEach((b)->{
                            JSONObject object = (JSONObject) b;
                            Role role = new Role(object.getAsString("name"));
                            rolesSet.add(role);
                        });

                        accessControl.setRoles(rolesSet);

                    }else{
                        logger.warn("No role was added for your service");
                    }


                    if(rolesAuthorities != null){
                        Set<RoleAuthority> rolesAuthoritiesSet = new HashSet<>();
                        rolesAuthorities.keySet().forEach(key->{
                            RoleAuthority roleAuthority = new RoleAuthority();
                            ((JSONArray) rolesAuthorities.get(key)).forEach(values->{
                                roleAuthority.setAuthority(new Authority((String) values));
                            });
                            rolesAuthoritiesSet.add(roleAuthority);
                        });

                        accessControl.setAuthorityRoles(rolesAuthoritiesSet);
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
