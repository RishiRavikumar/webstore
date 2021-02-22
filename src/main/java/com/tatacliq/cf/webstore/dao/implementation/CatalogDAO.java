package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.ICatalogDAO;
import com.tatacliq.cf.webstore.dao.interfaces.ICategoryDAO;
import com.tatacliq.cf.webstore.domain.entities.Catalog;
import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CatalogDAO implements ICatalogDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public CatalogDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    @Override
    public void addCatalog(Catalog catalog) throws SQLException {
        String query = resourceBundle.getString("addCatalog");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, catalog.getName());
        preparedStatement.setString(2, catalog.getDescription());
        preparedStatement.setLong(3, catalog.getCatalogId());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addCategory(long catalog_id, Category category) throws SQLException {
        ICategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.addNewCategory(category);

        String query = resourceBundle.getString("addCatalogCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, catalog_id);
        preparedStatement.setLong(2, category.getCategoryId());
        preparedStatement.executeUpdate();
        conn.commit();
    }


    @Override
    public Catalog getCatalog(long catalog_id) throws SQLException {
        ResultSet resultSet;
        Catalog catalog = null;
        String query = resourceBundle.getString("getCatalog");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, catalog_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            catalog = new Catalog();
            catalog.setName(resultSet.getString(1));
            catalog.setDescription(resultSet.getString(2));
            catalog.setCatalogId(resultSet.getLong(3));
        }
        return catalog;
    }

    @Override
    public List<Catalog> getCatalogs() throws SQLException {
        ResultSet resultSet;
        Catalog catalog;
        List<Catalog> catalogList = new ArrayList<>();
        String query = resourceBundle.getString("getCatalogs");
        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            catalog = new Catalog();
            catalog.setName(resultSet.getString(1));
            catalog.setDescription(resultSet.getString(2));
            catalog.setCatalogId(resultSet.getLong(3));
            catalogList.add(catalog);
        }
        return catalogList;
    }

    @Override
    public List<Category> getCategories(long catalog_id) throws SQLException {
        ResultSet resultSet;
        List<Category> categories = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category;

        String query = resourceBundle.getString("getCatalogCategories");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, catalog_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            long category_id = resultSet.getLong(2);
            category = categoryDAO.getCategory(category_id);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void removeCatalog(long catalog_id) throws SQLException {
        String query = resourceBundle.getString("removeCatalog");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, catalog_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    private void deleteCategory(long category_id) throws SQLException {
        String query = resourceBundle.getString("removeCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void removeCategory(long category_id) throws SQLException {
        String query = resourceBundle.getString("removeCatalogCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category_id);
        preparedStatement.executeUpdate();
        conn.commit();

        deleteCategory(category_id);
    }

    @Override
    public void updateCatalog(Catalog catalog) throws SQLException {
        String query = resourceBundle.getString("updateCatalog");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, catalog.getName());
        preparedStatement.setString(2, catalog.getDescription());
        preparedStatement.setLong(3, catalog.getCatalogId());
        preparedStatement.executeUpdate();
        conn.commit();
    }
}
