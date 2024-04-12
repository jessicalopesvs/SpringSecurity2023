package com.example.springsecuritydemo.config;


import com.example.springsecuritydemo.CustomerDetailsManager;
import com.example.springsecuritydemo.User;
import com.example.springsecuritydemo.UserRepository;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private CustomerDetailsManager cm;

    @Bean
    public CommandLineRunner clr() {
        return (args -> {
            // Define user data with roles
            String[][] usersData = {
                    {"admin", "admin", "ROLE_USER,ROLE_ADMIN"},
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
                    user.setRoles(roles);
                    try {
                        userRepository.save(user);
                        log.info("User '{}' created successfully with roles: {}", username, roles);
                    } catch (Exception e) {
                        log.error("Failed to save user '{}': {}", username, e.getMessage());
                    }
                }
            }
        });
    }

}
