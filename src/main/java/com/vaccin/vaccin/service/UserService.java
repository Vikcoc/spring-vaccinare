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


    public String createUser(UserCreateDto userCreateDto){

        Optional<AuthDto> userOptional = userRepository.getByEmailWithPasswordAndRole(userCreateDto.getEmail());

        if (userOptional.isPresent()) {
            return "User with email already exists";
        }

        var user = new User(userCreateDto);
        user.setPassword(BCrypt.hashpw(userCreateDto.getPassword(), BCrypt.gensalt()));

        Optional<Role> userRoleOptional = roleRepository.findByRole("ROLE_USER");

        userRoleOptional.ifPresent(user::setRole);

        userRepository.save(user);

        return "Added user";
    }

    public List<UserDto> getDoctors(){
        var users = userRepository.getDoctors();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
