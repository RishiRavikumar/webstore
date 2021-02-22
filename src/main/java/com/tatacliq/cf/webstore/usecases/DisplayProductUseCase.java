package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.struts.actions.DisplayProductAction;

import java.sql.SQLException;

public class DisplayProductUseCase {
    public static void main(String[] args) throws SQLException {
        long productId = 102;

        DisplayProductAction displayProductAction = new DisplayProductAction(productId);
        displayProductAction.execute();
    }
}
