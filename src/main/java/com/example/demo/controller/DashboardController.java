package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        User user = userRepository.readByEmail(authentication.getName());
        model.addAttribute("username", user.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInUser", user);
        return "dashboard.html";
    }

    @RequestMapping("/employee")
    public String displayEmployeePage(Model model, Authentication authentication, HttpSession session){
        User user = userRepository.readByEmail(authentication.getName());
        model.addAttribute("username", user.getName());
        session.setAttribute("loggedInUser", user);
        return "employee.html";
    }
}
