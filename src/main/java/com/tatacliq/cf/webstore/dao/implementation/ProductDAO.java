package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.IProductDAO;
import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.domain.entities.Product;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductDAO implements IProductDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public ProductDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    @Override
    public void addItem(Item item) throws SQLException {
        String query = resourceBundle.getString("addItem");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, item.getItemId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getDescription());
        preparedStatement.setDouble(4, item.getPrice());
        preparedStatement.setBoolean(5, item.isAvailable());
        preparedStatement.setLong(6, item.getProductId());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public List<Item> getItems(long productId) throws SQLException {
        ResultSet resultSet;
        List<Item> items = new ArrayList<>();
        Item item;

        String query = resourceBundle.getString("getItems");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, productId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            item = new Item();
            item.setItemId(resultSet.getLong(1));
            item.setName(resultSet.getString(2));
            item.setDescription(resultSet.getString(3));
            item.setPrice(resultSet.getDouble(4));
            item.setAvailable(resultSet.getBoolean(5));
            item.setProductId(resultSet.getLong(6));
            items.add(item);
        }

        return items;
    }

    @Override
    public Product getProduct(long productId) throws SQLException {
        ResultSet resultSet;
        Product product;
        String query = resourceBundle.getString("getProduct");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, productId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        product = new Product();
        product.setProductId(resultSet.getLong(1));
        product.setName(resultSet.getString(2));
        product.setDescription(resultSet.getString(3));
        product.setCost(resultSet.getDouble(4));
        product.setUnits(resultSet.getLong(5));

        return product;
    }

    @Override
    public Category getCategory(long productId) throws SQLException {
        ResultSet resultSet;
        Category category;
        String query = resourceBundle.getString("getProductCategory");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, productId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        long categoryId = resultSet.getLong(1);
        category = new CategoryDAO().getCategory(categoryId);

        return category;
    }

    @Override
    public boolean hasDisplayableItem(long productId) throws SQLException {
        ResultSet resultSet;

        String query = resourceBundle.getString("countItems");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, productId);
        resultSet = preparedStatement.executeQuery();

        return resultSet.getLong(1) > 0;
    }

    @Override
    public long getItemCount(long productId) throws SQLException {
        ResultSet resultSet;

        String query = resourceBundle.getString("countItems");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, productId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public void removeItem(long item_id) throws SQLException {
        String query = resourceBundle.getString("removeItem");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, item_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }
}
