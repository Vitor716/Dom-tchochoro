package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RegisterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class DashboardController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    RegisterService registerService;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        User user = userRepository.readByEmail(authentication.getName());
        model.addAttribute("username", user.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInUser", user);
        return "dashboard.html";
    }

    @RequestMapping("/employee")
    public ModelAndView displayEmployeePage(Model model, Authentication authentication, HttpSession session){
        User user = userRepository.readByEmail(authentication.getName());
        model.addAttribute("user", new User());
        model.addAttribute("username", user.getName());
        session.setAttribute("loggedInUser", user);
        List<User> listAdm = employeeService.getAdmList();
        ModelAndView modelAndView = new ModelAndView("employee.html");
        modelAndView.addObject("listAdms", listAdm);
        return modelAndView;
    }

    String registerAdm = "registerAdm.html";

    @RequestMapping("/registerAdm")
    public String diplayRegisterAdmPage(Model model){
        model.addAttribute("user", new User());
        return registerAdm;
    }

    @RequestMapping(value = "/saveAdm", method = {RequestMethod.POST})
    public String saveAdm(@Valid @ModelAttribute("user") User user, Errors errors){
        if(errors.hasErrors()){
            return registerAdm;
        }
        boolean isSaved = registerService.saveAdmRegister(user);
        if(isSaved) {
            return "redirect:/employee";
        }else{
            return registerAdm;
        }
    }
}
