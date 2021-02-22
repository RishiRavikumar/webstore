package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.domain.entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    void addItem(Item item) throws SQLException;
    List<Item> getItems(long productId) throws SQLException;
    Product getProduct(long productId) throws SQLException;
    Category getCategory(long productId) throws SQLException;
    boolean hasDisplayableItem(long productId) throws SQLException;
    long getItemCount(long productId) throws SQLException;
    void removeItem(long itemId) throws SQLException;
}
