package com.example.springsecuritydemo.config;


import com.example.springsecuritydemo.MyUserDetailsManager;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.repositories.RoleRepository;
import com.example.springsecuritydemo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


@Configuration
@Slf4j
public class DBInitializerConfig {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsManager cm;



    @Bean
    public CommandLineRunner clr(RoleRepository roleRepository) {
        return (args -> {
            // Define user data with roles
            String[][] usersData = {
                    {"admin", "admin", "ROLE_ADMIN"},
                    {"user", "user", "ROLE_USER"},
                    // Add more users and their roles as needed
            };

            for (String[] data : usersData) {
                String username = data[0];
                String password = data[1];
                String roles = data[2];

                Optional<User> existingUser = userRepository.findByUsername(username);
                if (existingUser.isPresent()) {
                    log.info("User '{}' already exists. Skipping initialization.", username);
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(passwordEncoder.encode(password));
                    user.setActive(true);
                    user.setRoles(Collections.singletonList(roleRepository.findByName(roles)));
                    try {
                        cm.createUser(user);
                        log.info("User '{}' created successfully with roles: {}", username, roles);
                    } catch (Exception e) {
                        log.error("Failed to save user '{}': {}", username, e.getMessage());
                    }
                }
            }
        });
    }

}
