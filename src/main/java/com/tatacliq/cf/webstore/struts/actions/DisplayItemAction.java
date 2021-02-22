package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.ItemDAO;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class DisplayItemAction {
    long itemId;

    public void execute() throws SQLException {
        ItemDAO itemDAO = new ItemDAO();
        System.out.println(itemDAO.getItem(itemId));
    }
}
