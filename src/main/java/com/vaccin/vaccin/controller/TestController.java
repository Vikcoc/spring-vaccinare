package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String Test()
    {
        return "Test successful";
    }

    @GetMapping("/test2")
    public void Test2() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * x;

        List.of(4,6,7).stream()
                .map(f.compose(g))
                .forEach(x -> System.out.println(x + " "));


    }

    @PostMapping("/user")
    public String PostUser(@RequestBody UserCreateDto userCreateDto){
        return userService.createUser(userCreateDto);
    }

    @GetMapping("/users")
    public List<UserDto> GetDoctors(){
        return userService.getDoctors();
    }
}
