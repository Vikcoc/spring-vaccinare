package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.exception.BadRequestException;
import com.vaccin.vaccin.exception.NotFoundException;
import com.vaccin.vaccin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserCreateDto userCreateDto) throws BadRequestException {

        UserDto userDto = userService.createUser(userCreateDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }

    @GetMapping("/users/get/{userId}")
    public ResponseEntity<UserDto> retrieveUser(@PathVariable  Long userId) throws NotFoundException {
        UserDto userDto = userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/users/edit/{userId}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long userId, @RequestBody UserCreateDto userCreateDto) throws BadRequestException {

        UserDto userDto = userService.updateUser(userId, userCreateDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/users/promote/{userId}")
    public ResponseEntity<UserDto> promoteUser(@PathVariable long userId) throws BadRequestException {
        UserDto userDto = userService.promoteToDoctor(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
