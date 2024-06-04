package org.example.decorshop.Repository;

import org.example.decorshop.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
}
