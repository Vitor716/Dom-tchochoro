package com.example.demo.service;

import com.example.demo.constants.DomConstants;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveUserRegister(User user) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(DomConstants.CLIENT_ROLE);
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(DomConstants.CLIENT_ROLE);
        user = userRepository.save(user);
        if(null != user && user.getUserId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public boolean saveAdmRegister(User user) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(DomConstants.ADMIN_ROLE);
        user.setRoles(role);
        user.getRoles().setRoleId(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(DomConstants.ADMIN_ROLE);
        user = userRepository.save(user);
        if(null != user && user.getUserId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

}
