package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    public boolean saveUserRegister(User user) {
        boolean isSaved = false;
        User savedUser = userRepository.save(user);
        if(savedUser.getId() > 0){
            isSaved = true;
        }
        return  isSaved;
    }
}
