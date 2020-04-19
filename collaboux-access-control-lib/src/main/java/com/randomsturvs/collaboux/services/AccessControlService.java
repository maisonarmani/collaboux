package com.randomsturvs.collaboux.services;

import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.repository.RoleAuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class AccessControlService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;

    public List<Role> getUserRoles(User user){

        CriteriaBuilder cb =  sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(cb.equal(root.get("id"), user.getId()));
        return sessionFactory.openSession().createQuery(criteriaQuery).getResultList();
    }

    public void addUserRole(User user, Role role){
        // set user role

    }

}