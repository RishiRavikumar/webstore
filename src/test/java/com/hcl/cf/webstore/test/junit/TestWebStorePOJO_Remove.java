package com.hcl.cf.webstore.test.junit;

import com.tatacliq.cf.webstore.dao.implementation.CatalogDAO;
import com.tatacliq.cf.webstore.dao.interfaces.ICatalogDAO;
import com.tatacliq.cf.webstore.domain.entities.Catalog;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestWebStorePOJO_Remove {
    private IWebStoreFacade webStoreFacade;

    @BeforeEach
    void setUp() {
        webStoreFacade = new WebStorePOJO();
    }

    @Test
    @DisplayName("Test remove catalog")
    public void testRemoveCatalog() throws SQLException {
        ICatalogDAO catalogDAO = new CatalogDAO();
        Catalog catalog = webStoreFacade.createCatalog();
        catalog.setCatalogId(123);
        catalog.setName("catalogName1");
        catalog.setDescription("desc1");

        catalogDAO.addCatalog(catalog);
        catalogDAO.removeCatalog(123);

        assertFalse(catalogDAO.getCatalog(123) != null);
    }
}
