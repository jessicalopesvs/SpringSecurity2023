package com.example.springsecuritydemo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails {

    private static final long serailVersionUID =-1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private boolean active;
    @Column
    private String roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(roles));
        return result;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
