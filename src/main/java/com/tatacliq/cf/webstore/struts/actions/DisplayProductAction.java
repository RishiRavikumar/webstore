package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.ProductDAO;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class DisplayProductAction {
    long productId;

    public void execute() throws SQLException {
        ProductDAO productDAO = new ProductDAO();


        System.out.println(productDAO.getProduct(productId));
    }
}
