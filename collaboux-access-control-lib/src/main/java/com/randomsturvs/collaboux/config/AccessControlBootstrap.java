package com.randomsturvs.collaboux.config;

import com.randomsturvs.collaboux.entity.Authority;
import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.RoleAuthority;
import com.randomsturvs.collaboux.enums.Domain;
import com.randomsturvs.collaboux.model.ModifiedSet;
import com.randomsturvs.collaboux.repository.AuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleAuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.FileReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

@Configuration
public class AccessControlBootstrap implements InitializingBean {

    @Autowired
    private ConfigProperties properties;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    Session session;
    CriteriaBuilder criteriaBuilder;


    Logger logger = LoggerFactory.getLogger(AccessControlBootstrap.class);
    @Override
    public void afterPropertiesSet() throws Exception, ExecutionException {
        session = sessionFactory.openSession();
        criteriaBuilder = sessionFactory.getCriteriaBuilder();
        processAccessControlJSON();
    }


    private AccessControl processAccessControlJSON(){
        JSONParser jsonParser = new JSONParser(0);
        URL resource = getClass().getClassLoader().getResource(properties.getFilePath());
        AccessControl accessControl = new AccessControl();

        if (resource != null){
            try (FileReader reader = new FileReader(resource.getFile()))
            {
                Object obj = jsonParser.parse(reader);
                JSONArray accessControls = (JSONArray) obj;
                accessControls.forEach((json)->{
                    JSONObject jsonObject = (JSONObject) json;
                    accessControl.setName(jsonObject.getAsString("name"));
                    accessControl.setUpdateMode(AccessControl.UpdateMode.valueOf(jsonObject.getAsString("updateMode")));

                    JSONArray authorities = (JSONArray) jsonObject.get("authorities");
                    JSONArray roles = (JSONArray) jsonObject.get("roles");
                    JSONObject rolesAuthorities = (JSONObject) jsonObject.get("rolesAuthorities");


                    if (authorities != null){
                        ModifiedSet< Authority> authorityModifiedSet = new ModifiedSet<>();
                        authorities.forEach((b)->{
                            JSONObject object = (JSONObject) b;
                            Authority authority = new Authority(object.getAsString("name"));
                            authority.setDomain(Domain.valueOf(object.getAsString("domain")));
                            authority.setFriendlyName(object.getAsString("friendlyName"));
                            authority.setDescription(object.getAsString("description"));
                            authorityModifiedSet.add(authority);
                            saveAuthority(authority);

                        });

                        accessControl.setAuthorities(authorityModifiedSet);
                    }else{
                        logger.warn("No authority was added for your service");
                    }


                    if(roles != null){
                        ModifiedSet<Role> rolesModifiedSet = new ModifiedSet<>();
                        roles.forEach((b)->{
                            JSONObject object = (JSONObject) b;
                            Role role = new Role(object.getAsString("name"));
                            saveRole(role);
                            rolesModifiedSet.add(role);
                        });

                        accessControl.setRoles(rolesModifiedSet);

                    }else{
                        logger.warn("No role was added for your service");
                    }

                    if(rolesAuthorities != null){
                        ModifiedSet<RoleAuthority> rolesAuthoritiesModifiedSet = new ModifiedSet<>();
                        rolesAuthorities.keySet().forEach(key->{
                            RoleAuthority roleAuthority = new RoleAuthority();
                            ((JSONArray) rolesAuthorities.get(key)).forEach(values->{
                                roleAuthority.setAuthority(new Authority((String) values));
                            });

                            saveRoleAuthority(roleAuthority);
                            rolesAuthoritiesModifiedSet.add(roleAuthority);

                        });

                        accessControl.setAuthorityRoles(rolesAuthoritiesModifiedSet);
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return accessControl;


    }

    private void saveRole(Role role) {
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), role.getName()));
        Query<Role> query = this.session.createQuery(criteriaQuery);
        if(query.getResultList().isEmpty()){
            roleRepository.save(role);
        }

        query.getResultList().stream().forEach(e->{
            e.setName(role.getName());
            roleRepository.save(e);
        });
    }

    private void saveAuthority(Authority authority) {
        CriteriaQuery<Authority> criteriaQuery = criteriaBuilder.createQuery(Authority.class);
        Root<Authority> root = criteriaQuery.from(Authority.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), authority.getName()));
        Query<Authority> query = this.session.createQuery(criteriaQuery);

        if(query.getResultList().isEmpty()){
            authorityRepository.save(authority);
        }

        query.getResultList().stream().forEach(e->{
            e.setName(authority.getName());
            e.setDomain(authority.getDomain());
            e.setFriendlyName(authority.getFriendlyName());
            e.setDescription(authority.getDescription());
            authorityRepository.save(e);
        });
    }

    private void saveRoleAuthority(RoleAuthority roleAuthority) {
       // get id of the key role
        // then get id of each authority in the role
        CriteriaQuery<Authority> criteriaQuery = criteriaBuilder.createQuery(Authority.class);
        Root<Authority> root = criteriaQuery.from(Authority.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), roleAuthority.getAuthority().getName()));
        Query<Authority> query = this.session.createQuery(criteriaQuery);



    }
}
