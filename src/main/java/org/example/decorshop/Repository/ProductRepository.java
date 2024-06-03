package org.example.decorshop.Repository;

import org.example.decorshop.Model.Category;
import org.example.decorshop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Set<Product> findAllByCategories(Category category);
}
