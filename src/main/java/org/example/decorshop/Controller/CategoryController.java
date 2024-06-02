package org.example.decorshop.Controller;



import org.example.decorshop.Controller.DTO.CategoryCreateDTO;
import org.example.decorshop.Model.Category;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/{id}")
    public String category(@PathVariable Integer id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        List<Product> products = category.getProducts();
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "category";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        categoryRepository.save(category);
        return "success";
    }
}