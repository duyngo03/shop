package org.example.decorshop.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;
}
