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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
    @Autowired
    RegisterService registerService;
    String register= "register.html";

    @RequestMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("user", new User());
        return register;
    }



    @RequestMapping(value ="/saveUser",method = { RequestMethod.POST})
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
