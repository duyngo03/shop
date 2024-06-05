package org.example.decorshop.Controller;

import org.example.decorshop.Model.Blog;
import org.example.decorshop.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/blog")
    public String blog(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blog";
    }
}
