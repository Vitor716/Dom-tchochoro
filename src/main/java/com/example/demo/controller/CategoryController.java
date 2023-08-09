package com.example.demo.controller;

import com.example.demo.model.Categories;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;



    @RequestMapping("/categories")
    public String diplayCategories(Model model){
        model.addAttribute("categories", new Categories());
        return "categories";
    }

    @RequestMapping(value = "/saveCategory", method = {RequestMethod.POST})
    public String saveCategory(@Valid @ModelAttribute("categories")Categories categories, Errors errors){
        if(errors.hasErrors()){
            return "home";
        }
        boolean isSaved = categoryService.saveCategories(categories);
        return "redirect:/categories";
    }
}
