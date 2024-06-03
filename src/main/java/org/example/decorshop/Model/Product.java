package org.example.decorshop.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="text")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "isHot")
    private boolean isHot;

    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

}
