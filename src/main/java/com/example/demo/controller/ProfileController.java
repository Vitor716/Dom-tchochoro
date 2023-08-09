package com.example.demo.controller;

import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session, Authentication authentication){
        String email = authentication.getName();
        Profile profile = profileService.getUserProfileDorDisplay(email);
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @RequestMapping("/editProfile")
    public ModelAndView editProfile(Model model, HttpSession session, Authentication authentication){
        String email = authentication.getName();
        Profile profile = profileService.getUserProfileForEdition(email);
        ModelAndView modelAndView = new ModelAndView("editProfile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }


    @RequestMapping("/myProfile")
    public String showProfileDetails(Model model, HttpSession session, Authentication authentication){
        String email = authentication.getName();
        User user = profileService.getUserProfileDetails(email);

        model.addAttribute("username", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInUser", user);
        return "myProfile.html";
    }



    @PostMapping("/updateUserProfile")
    public String updateUserProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                HttpSession session) {
        if (errors.hasErrors()) {
            return "myProfile.html";
        }
        User user = (User) session.getAttribute("loggedInUser");
        User savedUser = profileService.updateUserProfile(user, profile);
        session.setAttribute("loggedInUser", savedUser);
        return "redirect:/editProfile";
    }

    @PostMapping("/updateAdminProfile")
    public String updateAdminProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                HttpSession session) {
        if (errors.hasErrors()) {
            return "profile.html";
        }
        User user = (User) session.getAttribute("loggedInUser");
        User savedUser = profileService.updateAdminProfile(user, profile);
        session.setAttribute("loggedInUser", savedUser);
        return "redirect:/displayProfile";
    }

}
