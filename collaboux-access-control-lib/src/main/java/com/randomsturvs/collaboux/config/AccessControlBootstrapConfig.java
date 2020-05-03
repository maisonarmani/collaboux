package com.randomsturvs.collaboux.config;

import com.randomsturvs.collaboux.entity.Authority;
import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.RoleAuthority;
import com.randomsturvs.collaboux.enums.DomainEnum;
import com.randomsturvs.collaboux.model.ModifiedSet;
import com.randomsturvs.collaboux.properties.ConfigProperties;
import com.randomsturvs.collaboux.repository.AuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleAuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.io.FileReader;
import java.net.URL;
import java.util.Optional;

@Configuration
public class AccessControlBootstrapConfig implements InitializingBean {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private ConfigProperties properties;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    final Logger logger = LoggerFactory.getLogger(AccessControlBootstrapConfig.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        processAccessControlJSON();
    }

    private AccessControlConfig processAccessControlJSON(){
        JSONParser jsonParser = new JSONParser(0);
        URL resource = getClass().getClassLoader().getResource(properties.getFilePath());
        AccessControlConfig accessControlConfig = new AccessControlConfig();

        if (resource != null){
            try (FileReader reader = new FileReader(resource.getFile()))
            {
                Object obj = jsonParser.parse(reader);
                JSONArray accessControls = (JSONArray) obj;
                accessControls.forEach((json)->{
                    JSONObject jsonObject = (JSONObject) json;
                    accessControlConfig.setName(jsonObject.getAsString("name"));
                    accessControlConfig.setUpdateMode(AccessControlConfig.UpdateMode.valueOf(jsonObject.getAsString("updateMode")));

                    JSONArray authorities = (JSONArray) jsonObject.get("authorities");
                    JSONArray roles = (JSONArray) jsonObject.get("roles");
                    JSONObject rolesAuthorities = (JSONObject) jsonObject.get("rolesAuthorities");


                    if (authorities != null){
                        ModifiedSet< Authority> authorityModifiedSet = new ModifiedSet<>();
                        authorities.forEach((b)->{
                            JSONObject object = (JSONObject) b;
                            Authority authority = new Authority(object.getAsString("name"));
                            authority.setDomainEnum(DomainEnum.valueOf(object.getAsString("domain")));
                            authority.setFriendlyName(object.getAsString("friendlyName"));
                            authority.setDescription(object.getAsString("description"));
                            authorityModifiedSet.add(authority);
                            saveAuthority(authority);

                        });

                        accessControlConfig.setAuthorities(authorityModifiedSet);
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

                        accessControlConfig.setRoles(rolesModifiedSet);

                    }else{
                        logger.warn("No role was added for your service");
                    }

                    if(rolesAuthorities != null){
                        ModifiedSet<RoleAuthority> rolesAuthoritiesModifiedSet = new ModifiedSet<>();
                        rolesAuthorities.keySet().forEach(key->{
                            RoleAuthority roleAuthority = new RoleAuthority();
                            ((JSONArray) rolesAuthorities.get(key)).forEach(values->{
                                roleAuthority.setAuthority(new Authority((String) values));
                                roleAuthority.setRole(new Role((String) key));

                                Long roleId = roleRepository.getIdWithName(roleAuthority.getRole().getName());
                                Long authorityId = authorityRepository.getIdWithName(roleAuthority.getAuthority().getName());

                                if(roleId != null && authorityId != null){
                                    roleAuthority.getAuthority().setId(authorityId);
                                    roleAuthority.getRole().setId(roleId);
                                    rolesAuthoritiesModifiedSet.add(roleAuthority);
                                }
                            });

                            try{
                                roleAuthorityRepository.truncate();
                            }
                            catch (PersistenceException e){
                                logger.warn("Truncate must have caused some issues");
                            }
                            roleAuthorityRepository.saveAll(rolesAuthoritiesModifiedSet);
                        });
                        accessControlConfig.setAuthorityRoles(rolesAuthoritiesModifiedSet);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accessControlConfig;
    }

    private void saveRole(Role role) {
        Example<Role> roleExample = Example.of(role,ExampleMatcher.matchingAll().withIgnoreCase());
        Optional<Role> roleOptional = roleRepository.findOne(roleExample);
        if(roleOptional.isPresent() ){
            role.setId(roleOptional.get().getId());
        }
        roleRepository.save(role);

    }

    private void saveAuthority(Authority authority) {
        Example<Authority> authorityExample = Example.of(authority,ExampleMatcher.matchingAll().withIgnorePaths("description").withIgnorePaths("friendly_name"));
        Optional<Authority> authorityOptional = authorityRepository.findOne(authorityExample);
        if(authorityOptional.isPresent() ){
            authority.setId(authorityOptional.get().getId());
        }
        authorityRepository.save(authority);

    }
}
