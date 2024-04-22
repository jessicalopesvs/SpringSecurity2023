package com.example.springsecuritydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @GetMapping("/")
    public String index(){
        return "connected";
    }

    @GetMapping("/home")
    public String home(){
        return "success";
    }

    @GetMapping("/authority")
    public String authority(){
        return "Has USER authority";
    }

    @GetMapping("/admin")
    public String role(){
        return "Has ADMIN role";
    }
}
