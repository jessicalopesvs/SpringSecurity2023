package com.example.springsecuritydemo.config;


import com.example.springsecuritydemo.User;
import com.example.springsecuritydemo.UserRepository;
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

        String user = "user";
        String admin = "admin";

        return (args -> {
            Optional<User> o = userRepository.findByUsername("admin");
            if (o.isPresent()) {
                log.info("User 'user' already exists. Skipping initialization.");
            } else {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(passwordEncoder.encode("admin"));
                u.setActive(true);
                u.setRoles("ROLE_ADMIN");
                try {
                    userRepository.save(u);
                    log.info("User 'admin' created successfully.");
                } catch (Exception e) {
                    log.error("Failed to save user 'user': {}", e.getMessage());
                }
            }
        });
    }

}
