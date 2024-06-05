package org.example.decorshop.Controller;

import org.example.decorshop.Model.Blog;
import org.example.decorshop.Model.Cart;
import org.example.decorshop.Model.Product;
import org.example.decorshop.Model.Users;
import org.example.decorshop.Service.BlogService;
import org.example.decorshop.Service.CartService;
import org.example.decorshop.Service.ProductService;
import org.example.decorshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CartService cartService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Product> products = productService.findAll();
        products = products.stream().filter(Product::isHot).toList();
        Product firstHotProduct = new Product();
        if(products.size() != 0) {
            firstHotProduct = products.get(0);
        }
        products = products.stream().skip(1).toList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = authentication.getName();
            Users user = userService.findByUserName(username).orElse(null);
            model.addAttribute("user", user);
        }
        List<Blog> blogs = blogService.getAllBlogs();
        List<Blog> latestBlog = blogs.stream().skip(0).limit(3).toList();
        model.addAttribute("firstHotProducts", firstHotProduct);
        model.addAttribute("hotProducts", products);
        model.addAttribute("latestBlog", latestBlog);
        return "index";
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }
}
