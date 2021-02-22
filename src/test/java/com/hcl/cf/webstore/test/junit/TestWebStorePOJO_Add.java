package com.hcl.cf.webstore.test.junit;

import com.tatacliq.cf.webstore.dao.implementation.CatalogDAO;
import com.tatacliq.cf.webstore.dao.interfaces.ICatalogDAO;
import com.tatacliq.cf.webstore.domain.entities.Catalog;
import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestWebStorePOJO_Add {
    private IWebStoreFacade webStoreFacade;

    @BeforeEach
    void setUp() {
        webStoreFacade = new WebStorePOJO();
    }

    @Test
    @DisplayName("Test add catalog")
    public void testAddCatalog() throws SQLException {
        ICatalogDAO catalogDAO = new CatalogDAO();
        Catalog catalog = webStoreFacade.createCatalog();
        catalog.setCatalogId(123);
        catalog.setName("catalogName1");
        catalog.setDescription("desc1");

        catalogDAO.addCatalog(catalog);

        Catalog fetchedCatalog = catalogDAO.getCatalog(123);

        assertEquals(fetchedCatalog.getCatalogId(), catalog.getCatalogId());
        assertEquals(fetchedCatalog.getName(), catalog.getName());
        assertEquals(fetchedCatalog.getDescription(), catalog.getDescription());

        catalogDAO.removeCatalog(123);
    }

    @Test
    @DisplayName("Test add catalog to category")
    public void testAddCatalogCategory() throws SQLException {

        /*
            Add Catalog
         */
        ICatalogDAO catalogDAO = new CatalogDAO();
        Catalog catalog = webStoreFacade.createCatalog();
        catalog.setCatalogId(123);
        catalog.setName("catalogName1");
        catalog.setDescription("desc1");
        catalogDAO.addCatalog(catalog);

        /*
            Add Category
         */
        Category category = webStoreFacade.createCategory();
        category.setCategoryId(34234);
        category.setName("categoryName1");
        category.setDescription("Category description");
        category.setSubCategory(null);

        catalogDAO.addCategory(catalog.getCatalogId(), category);

        catalogDAO.removeCategory(34234);
        catalogDAO.removeCatalog(123);
    }

}
