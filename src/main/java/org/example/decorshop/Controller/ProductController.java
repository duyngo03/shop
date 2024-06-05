package org.example.decorshop.Controller;

import org.example.decorshop.Controller.DTO.ProductCreateDTO;
import org.example.decorshop.Model.Category;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Service.CategoryService;
import org.example.decorshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        ProductCreateDTO productCreateDTO = new ProductCreateDTO();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("productCreateDTO", productCreateDTO);
        return "create-product";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productDTO") ProductCreateDTO productCreateDTO) {
        try {
            // Create product
            Product product = new Product();
            product.setName(productCreateDTO.getName());
            product.setPrice(productCreateDTO.getPrice());
            product.setDescription(productCreateDTO.getDescription());
            product.setDiscount(productCreateDTO.getDiscount());
            product.setPrice(productCreateDTO.getPrice());
            product.setHot(productCreateDTO.isHot());
            product.setImageUrl(productCreateDTO.getImageUrl());


            String [] categoryNames = productCreateDTO.getCategory().split(",");
            List<Category> categories = new ArrayList<>();
            for(int i = 0; i < categoryNames.length; i++) {
//                Category category = categoryService.findByName(categoryNames[i]);
                Category category  = categoryService.findById(Integer.parseInt(categoryNames[i])).orElseThrow(() -> new RuntimeException("Category not found: "));
                categories.add(category);
            }
            product.setCategories(categories);
            productService.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }

    @GetMapping("/{id}")
    public String product(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        Set<Product> sameProducts = new HashSet<>();
        for (Category category : product.getCategories()) {
            Set<Product> products = productService.findAllByCategories(category);
            sameProducts.addAll(products);
        }
        sameProducts.remove(product);
        Product fistProduct = sameProducts.iterator().next();
        sameProducts.remove(fistProduct);
        model.addAttribute("quantity", 1);
        model.addAttribute("fistProduct", fistProduct);
        model.addAttribute("sameProducts", sameProducts);
        model.addAttribute("product", product);
        return "product";
    }
}
