package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.role.role = 'ROLE_DOCTOR'")
    public List<User> getDoctors();

    @Query("SELECT new com.vaccin.vaccin.dto.AuthDto(u.email, u.password, u.role.role) FROM User u WHERE u.email = ?1")
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);

}
