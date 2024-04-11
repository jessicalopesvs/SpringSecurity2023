package com.example.springsecuritydemo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@Configuration
@Slf4j
public class DBInitializerConfig {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner clr() {

        return (args -> {
            Optional<User> o = userRepository.findByUsername("user");
            if (o.isEmpty()) {
                User u = new User();
                u.setUsername("user");
                u.setPassword(passwordEncoder.encode("password"));
                u.setActive(true);
                u.setRoles("ROLE_ADMIN");
                userRepository.save(u);
            }
        });

    }

}
