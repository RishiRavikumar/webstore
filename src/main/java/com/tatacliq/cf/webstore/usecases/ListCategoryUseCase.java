package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.struts.actions.ListCategoryAction;

import java.sql.SQLException;

public class ListCategoryUseCase {
    public static void main(String[] args) throws SQLException {
        long catalogId = 1;

        ListCategoryAction listCategoryAction = new ListCategoryAction(catalogId);
        listCategoryAction.execute();
    }
}
