package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.model.Privilege;
import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.repositories.PrivilegeRepository;
import com.example.springsecuritydemo.repositories.RoleRepository;
import com.example.springsecuritydemo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class SetupUserData implements ApplicationListener<ContextRefreshedEvent> {

    boolean isSetup = false;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isSetup) return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
    }

    @Transactional
    public void createRoleIfNotFound(String roleUser, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(roleUser);
        if (role == null) {
            role = new Role(roleUser);
            role.setName(roleUser);
            role.setPrivileges(privileges);
            roleRepository.save(role);
            log.info("Role {} created successfully", role.getName());
        }
        log.info("Role {} already exists", role.getName());

    }


    @Transactional
    public Privilege createPrivilegeIfNotFound(String privilegeName) {
        Privilege privilege = privilegeRepository.findByName(privilegeName);

        if (privilege == null) {
            privilege = new Privilege(privilegeName);
            privilege.setName(privilegeName);
            privilegeRepository.save(privilege);
            log.info("Privilege {} created successfully", privilege.getName());

        }
        return privilege;
    }


    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();


    }
}
