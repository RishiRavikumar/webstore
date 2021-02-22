package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.domain.entities.Cart;
import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddItemToCartAction {

    private Cart cart;
    private Item item;
    private Product product;

    public void execute() {
        cart.addItem(item);
        cart.addProduct(product, 1);

        System.out.println("Cart Items: ");
        cart.getItems()
                .stream()
                .forEach(System.out::println);
        System.out.println();
    }
}
