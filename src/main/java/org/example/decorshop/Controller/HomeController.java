package org.example.decorshop.Controller;

import org.example.decorshop.Model.Product;
import org.example.decorshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Product> products = productService.findAll();
        products = products.stream().filter(Product::isHot).toList();
        Product firstHotProduct = new Product();
        if(products.size() != 0) {
            firstHotProduct = products.get(0);
        }
        products = products.stream().skip(0).toList();
        model.addAttribute("firstHotProducts", firstHotProduct);
        model.addAttribute("hotProducts", products);
        return "index";
    }
}
