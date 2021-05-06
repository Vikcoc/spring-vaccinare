package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    public List<User> getDoctors();
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);
}
