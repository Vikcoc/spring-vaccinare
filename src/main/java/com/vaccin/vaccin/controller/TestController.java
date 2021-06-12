package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
public class TestController {

    private UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/test")
    public String test() {

        return "Test successful";
    }

    @GetMapping("/test2/{pass}")
    public String test2(@PathVariable String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    @GetMapping("/users")
    public List<UserDto> getDoctors(){
        return userService.getDoctors();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminTest")
    public String udminTest(){
        return "Admin test";
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping("/doctorTest")
    public String doctorTest(){
        return "Doctor test";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/userTest")
    public String userTest(){
        return "User test";
    }

    @GetMapping("/throw")
    public ResponseEntity<String> throwFunction() throws Exception {
        if(true)
            throw new Exception();
        return new ResponseEntity<>("User test", HttpStatus.OK);
    }
}
