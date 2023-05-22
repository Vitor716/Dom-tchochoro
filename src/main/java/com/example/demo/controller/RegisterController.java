package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RegisterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user")User user, Errors errors){
        if(errors.hasErrors()){
            log.error("Register user form validation failed due to: " +errors.toString());
            return "register.html";
        };
        registerService.saveUserRegister(user);
        return "redirect:/register";
    }
}
