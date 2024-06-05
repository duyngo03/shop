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
    public String addToCart(@RequestParam Integer productId, @RequestParam int quantity,@RequestParam String currentUrl, Model model) {
        cartService.addToCart (productId, quantity);
        model.addAttribute("message", "Sản phẩm đã được thêm vào giỏ hàng!");
        return "redirect:" + currentUrl;
    }
//    @GetMapping("/products")
//    public String listProducts(Model model) {
//        model.addAttribute("products", productRepository.findAll());
//        return "products";
//    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Integer productId, Model model) {
        cartService.removeFromCart(productId);
        model.addAttribute("message", "Sản phẩm đã được xóa khỏi giỏ hàng!");
        return "redirect:/cart";
    }
}
