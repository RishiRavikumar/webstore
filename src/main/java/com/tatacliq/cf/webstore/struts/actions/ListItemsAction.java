package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.ProductDAO;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class ListItemsAction {
    long productId;

    public void execute() throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        productDAO.getItems(productId)
                .stream()
                .forEach(System.out::println);
    }
}
