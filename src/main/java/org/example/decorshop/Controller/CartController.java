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

    @GetMapping("/{cartId}")
    public String viewCart(@PathVariable Integer cartId, Model model) {
        Cart cart = cartService.getCartById(cartId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/{cartId}/add")
    public String addToCart(@PathVariable Integer cartId, @RequestParam Integer productId, @RequestParam int quantity) {
        cartService.addToCart(cartId, productId, quantity);
        return "redirect:/cart/" + cartId;
    }
}
