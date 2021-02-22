package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.ICategory;
import lombok.Data;

import java.util.Set;

@Data
public class Category implements ICategory {
    private long categoryId;
    private String name;
    private String description;
    private Set<Product> products;
    private Images images;
    private Category subCategory; // type check
}
