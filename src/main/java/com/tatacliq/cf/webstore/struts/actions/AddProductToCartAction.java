package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.domain.entities.Cart;
import com.tatacliq.cf.webstore.domain.entities.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddProductToCartAction {
    private Cart cart;
    private Product product;
    private long quantity;

    public void execute() {
        cart.addProduct(product, quantity);
    }
}
