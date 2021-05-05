package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String createUser(UserCreateDto userCreateDto){
        var user = new User(userCreateDto);

        userRepository.save(user);

        return "Ok";
    }

    public List<UserDto> getDoctors(){
        var users = userRepository.getDoctors();
        return users.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
    }
}
