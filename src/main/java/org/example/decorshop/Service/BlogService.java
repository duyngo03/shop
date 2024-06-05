package org.example.decorshop.Service;

import org.example.decorshop.Model.Blog;
import org.example.decorshop.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
