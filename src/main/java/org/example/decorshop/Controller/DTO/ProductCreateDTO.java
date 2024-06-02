package org.example.decorshop.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {
    public String name;
    public String description;
    public double price;
    public double discount;
    public String category;
    public String imageUrl;
    public boolean isHot  = false;
}
