package org.example.decorshop.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @ManyToMany(mappedBy = "categories")
    public List<Product> products;
}
