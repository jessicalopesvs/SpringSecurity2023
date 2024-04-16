package com.example.springsecuritydemo.repositories;

import com.example.springsecuritydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleUser);
}
