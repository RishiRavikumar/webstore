package com.tatacliq.cf.webstore.struts.actions;


import com.tatacliq.cf.webstore.dao.implementation.CatalogDAO;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class ListCategoryAction {
    private long catalogId;

    public void execute() throws SQLException {
        CatalogDAO catalogDAO = new CatalogDAO();

        catalogDAO.getCategories(catalogId)
                .stream()
                .forEach(System.out::println);
    }
}
