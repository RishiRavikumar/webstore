package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IItem;
import lombok.Data;

@Data
public class Item implements IItem {
    private long itemId;
    private String name;
    private String description;
    private double price;
    private long productId;
    private Images images;
    private boolean available;
}
