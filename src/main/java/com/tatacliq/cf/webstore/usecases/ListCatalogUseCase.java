package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.struts.actions.ListCatalogAction;

import java.sql.SQLException;

public class ListCatalogUseCase {
    public static void main(String[] args) throws SQLException {
        ListCatalogAction listCatalogAction = new ListCatalogAction();
        listCatalogAction.execute();
    }
}
