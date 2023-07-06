package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = {"", "/", "home"})
    public String displayHomePage(Model model, HttpSession session){
        User user = new User();
        model.addAttribute("username", user.getName());
        return "home.html";
    }
}
