package com.library.service;

import com.library.domain.Category;
import com.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Category saveCategory(Category category) {

        categoryRepository.save(category);

        return category;
    }
}
