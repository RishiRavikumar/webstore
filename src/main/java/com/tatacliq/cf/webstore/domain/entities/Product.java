package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IProduct;
import lombok.Data;

import java.util.Set;

@Data
public class Product implements IProduct {
    private long productId;
    private String name;
    private String description;
    private double cost;
    private long units;
    private Set<Item> items;
    private Images images;
}
