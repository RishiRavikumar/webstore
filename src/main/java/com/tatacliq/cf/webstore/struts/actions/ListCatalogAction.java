package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.CatalogDAO;

import java.sql.SQLException;

public class ListCatalogAction {
    public void execute() throws SQLException {
        CatalogDAO catalogDAO = new CatalogDAO();

        catalogDAO.getCatalogs()
                .stream()
                .forEach(System.out::println);
    }
}
