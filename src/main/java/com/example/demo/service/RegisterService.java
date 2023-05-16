package com.example.demo.service;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterService {
    public boolean saveUserRegister(User user){
        boolean isSaved = true;
        log.info(user.toString());
        return isSaved;
    }
}
