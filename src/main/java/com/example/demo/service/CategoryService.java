package com.example.demo.service;

import com.example.demo.model.Categories;
import com.example.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public boolean saveCategories(Categories categories){
        boolean isSaved = false;
        Categories savedCategory = categoryRepository.save(categories);
        if(null != savedCategory && savedCategory.getCategoryId()>0){
            isSaved = true;
        }
        return isSaved;
    }
}
