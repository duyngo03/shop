package org.example.decorshop.Repository;

import org.example.decorshop.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
