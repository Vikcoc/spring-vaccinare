package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getDoctors() {
        return entityManager.createQuery("select u from User u where u.doctor is not null")
                .getResultList();
    }
}
