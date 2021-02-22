package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.domain.entities.Cart;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.Date;

@AllArgsConstructor
public class ClearCartAndOrderAction {
    private Cart cart;
    private Date date;
    private long userId;
    private long creditCardId;
    private long addressId;

    public void execute() throws SQLException {
        new PlaceOrderAction(cart, date, userId, creditCardId, addressId).execute();

        cart.getItems().clear();
        cart.getProducts().clear();
    }
}
