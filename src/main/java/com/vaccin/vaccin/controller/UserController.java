package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public String registerUser(@RequestBody UserCreateDto userCreateDto) {

        return userService.createUser(userCreateDto);
    }
}
