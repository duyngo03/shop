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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "description", columnDefinition="text")
    public String description;

    @Column(name = "price")
    public double price;

    @Column(name = "discount")
    public double discount;

    @Column(name = "imageUrl")
    public String imageUrl;

    @Column(name = "isHot")
    public boolean isHot;

    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public List<Category> categories;

}
