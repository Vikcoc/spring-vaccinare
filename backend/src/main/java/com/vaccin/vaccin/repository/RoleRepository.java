package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.Role;
import com.vaccin.vaccin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
