package com.vaccin.vaccin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String Test()
    {
        return "Test successful";
    }
}
