package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.struts.actions.DisplayItemAction;

import java.sql.SQLException;

public class DisplayItemUseCase {
    public static void main(String[] args) throws SQLException {

        long itemId = 2;

        DisplayItemAction displayItemAction = new DisplayItemAction(itemId);
        displayItemAction.execute();
    }
}
