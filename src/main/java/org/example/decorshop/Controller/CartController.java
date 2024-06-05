package org.example.decorshop.Controller;

import org.example.decorshop.Model.Cart;
import org.example.decorshop.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String viewCart( Model model) {
        model.addAttribute("cart", cartService.getCartForCurrentUser());
//        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer productId, @RequestParam int quantity) {
        cartService.addToCart (productId, quantity);
        return "redirect:/cart";
    }
//    @GetMapping("/products")
//    public String listProducts(Model model) {
//        model.addAttribute("products", productRepository.findAll());
//        return "products";
//    }
}
