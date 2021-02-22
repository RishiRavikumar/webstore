package com.tatacliq.cf.webstore.domain.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.domain.entities.Product;

import java.util.List;

public interface ICart {
    boolean addProduct(Product product, long quantity);
    boolean setProductQuantity(Product product, long quantity);
    boolean removeProduct(long productId);
    Product getCartProduct(long productId);
    boolean addItem(Item item);
    boolean removeItem(long itemId);
    Item getCartItem(long itemId);
    long getItemCount();
    long getItemTotal();
    List<Item> getProductIds();
}
