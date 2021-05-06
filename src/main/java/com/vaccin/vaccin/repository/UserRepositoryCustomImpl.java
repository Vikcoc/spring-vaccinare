package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getDoctors() {
        return entityManager.createQuery("select u from User u where u.doctor is not null")
                .getResultList();
    }

    @Override
    public Optional<AuthDto> getByEmailWithPasswordAndRole(String email) {

        String query = "select new com.vaccin.vaccin.dto.AuthDto" +
                " (d.email, d.password, d.role.role) " +
                " from User d " +
                " where d.email = :email ";

        AuthDto authDto =
                entityManager.createQuery(query, AuthDto.class)
                        .setParameter("email", email)
                        .getSingleResult();

        return Optional.of(authDto);
    }
}
