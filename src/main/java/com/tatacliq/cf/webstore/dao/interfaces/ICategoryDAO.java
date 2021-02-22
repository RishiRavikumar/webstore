package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO {
    void addProduct(long category_id, Product product) throws SQLException;
    void addSubCategory(long category_id, Category category) throws SQLException;
    void addNewCategory(Category category) throws SQLException;
    Category getCategory(long categoryId) throws SQLException;
    List<Product> getProducts(long category_id) throws SQLException;
    List<Category> getSubCategories(long category_id);
    boolean hasProducts(long categoryId) throws SQLException;
    boolean hasSubCategories() throws SQLException;
    void removeProduct(long product_id) throws SQLException;
    void updateCategory(Category category) throws SQLException;
}
