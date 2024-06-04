package org.example.decorshop.Service;

import jakarta.transaction.Transactional;
import org.example.decorshop.Model.Cart;
import org.example.decorshop.Model.CartItem;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Repository.CartRepository;
import org.example.decorshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addToCart(Integer cartId, Integer productId, int quantity) {
//        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Cart cart = new Cart();
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        List<CartItem> cartItemList =  new ArrayList<>();
        cartItemList.add(cartItem);
        cart.setItems(cartItemList);
//        cart.getItems().add(cartItem);
        cartRepository.save(cart);
    }

    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
