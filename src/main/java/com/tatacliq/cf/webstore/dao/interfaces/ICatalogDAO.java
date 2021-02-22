package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Catalog;
import com.tatacliq.cf.webstore.domain.entities.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICatalogDAO {
    void addCatalog(Catalog catalog) throws SQLException;
    void addCategory(long catalog_id, Category category) throws SQLException;
    Catalog getCatalog(long catalog_id) throws SQLException;
    List<Catalog> getCatalogs() throws SQLException;
    List<Category> getCategories(long catalog_id) throws SQLException;
    void removeCatalog(long catalog_id) throws SQLException;
    void removeCategory(long category_id) throws SQLException;
    void updateCatalog(Catalog catalog) throws SQLException;
}
