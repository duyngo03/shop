package org.example.decorshop.Service;

import jakarta.transaction.Transactional;
import org.example.decorshop.Model.Cart;
import org.example.decorshop.Model.CartItem;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Model.Users;
import org.example.decorshop.Repository.CartRepository;
import org.example.decorshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void addToCart(Integer productId, int quantity) {
//        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        String username = getCurrentUsername();
        Users user = userService.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = user.getCart();
        if(cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        if(cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(cartItem);
        cartRepository.save(cart);
    }

    public Cart getCartForCurrentUser() {
        String username = getCurrentUsername();
        Users user = userService.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getCart() == null) {
            Cart cart = new Cart();
            user.setCart(cart);
        }
        //        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
        return user.getCart();
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }
}
