package com.example.springsecuritydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @GetMapping("/home")
    public String home(){
        return "success";
    }

    @GetMapping("/authority")
    public String authority(){
        return "Has authority";
    }

    @GetMapping("/admin")
    public String role(){
        return "Has role";
    }
}
