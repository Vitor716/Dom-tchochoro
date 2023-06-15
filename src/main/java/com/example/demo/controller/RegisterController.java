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
    String register= "register.html";

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("user", new User());
        return register;
    }

    @PostMapping(path ="/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, Errors errors) {
        if(errors.hasErrors()){
            return register;
        }
        boolean isSaved = registerService.saveUserRegister(user);
        if(isSaved) {
            return "redirect:/login?register=true";
        }else{
            return register;
        }
    }
}
