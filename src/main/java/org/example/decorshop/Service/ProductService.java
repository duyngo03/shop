package org.example.decorshop.Service;

import org.example.decorshop.Model.Category;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Set<Product> findAllByCategories(Category category) {
        return productRepository.findAllByCategories(category);
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

}
