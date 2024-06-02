package org.example.decorshop.Service;


import org.example.decorshop.Model.Category;
import org.example.decorshop.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
