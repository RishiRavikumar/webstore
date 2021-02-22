package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.ICatalog;
import lombok.Data;

import java.util.Set;

@Data
public class Catalog implements ICatalog {
    private long catalogId;
    private String name;
    private String description;
    private Set<Category> categories;
    private Images images;
}
