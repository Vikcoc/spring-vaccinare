package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.model.Role;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.repository.RoleRepository;
import com.vaccin.vaccin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto createUser(UserCreateDto userCreateDto) throws Exception {

        Optional<AuthDto> userOptional = userRepository.getByEmailWithPasswordAndRole(userCreateDto.getEmail());

        if (userOptional.isPresent()) {
            throw new Exception("User with email already exists");
        }

        User user;

        try {
            user = new User(userCreateDto);
        } catch (IllegalArgumentException e) {
            throw new Exception("Date format incorrect");
        }

        user.setPassword(BCrypt.hashpw(userCreateDto.getPassword(), BCrypt.gensalt()));

        Optional<Role> userRoleOptional = roleRepository.findByRole("ROLE_USER");
        userRoleOptional.ifPresent(user::setRole);

        user.setAppointed(false);

        return new UserDto(userRepository.save(user));

    }

    public List<UserDto> getDoctors(){
        var users = userRepository.getDoctors();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
