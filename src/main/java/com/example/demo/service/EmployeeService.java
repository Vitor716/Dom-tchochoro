package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<User> getAdmList(){
        return employeeRepository.findUsersByRoleId();
    }
}
