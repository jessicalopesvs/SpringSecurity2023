package com.example.springsecuritydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/portal").authenticated()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/**").permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    //DEPRECATED -> MADE FOR TEST THE FILTER CHAIN INITIALY

    //    @Bean
    //    public InMemoryUserDetailsManager userDetailsManager(){
    //        UserDetails user = User.withDefaultPasswordEncoder()
    //                .username("user")
    //                .password("pass")
    //                .authorities("USER")
    //                .build();
    //
    //
    //        UserDetails admin = User.withDefaultPasswordEncoder()
    //                .username("admin")
    //                .password("admin")
    //                .authorities("ADMIN")
    //                .build();
    //        return new InMemoryUserDetailsManager(user,admin);
    //    }
}


