package com.example.springsecuritydemo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class DBInitializerConfig {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MyUserDetailsManager cm;
//
//    @Bean
//    public CommandLineRunner clr() {
//        return (args -> {
//            // Define user data with roles
//            String[][] usersData = {
//                    {"admin", "admin", "ROLE_USER,ROLE_ADMIN"},
//                    {"user", "user", "ROLE_USER"},
//                    // Add more users and their roles as needed
//            };
//
//            for (String[] data : usersData) {
//                String username = data[0];
//                String password = data[1];
//                String roles = data[2];
//
//                Optional<User> existingUser = userRepository.findByUsername(username);
//                if (existingUser.isPresent()) {
//                    log.info("User '{}' already exists. Skipping initialization.", username);
//                } else {
//                    User user = new User();
//                    user.setUsername(username);
//                    user.setPassword(passwordEncoder.encode(password));
//                    user.setActive(true);
//                    user.setRoles(roles);
//                    try {
//                        userRepository.save(user);
//                        log.info("User '{}' created successfully with roles: {}", username, roles);
//                    } catch (Exception e) {
//                        log.error("Failed to save user '{}': {}", username, e.getMessage());
//                    }
//                }
//            }
//        });
//    }

}
