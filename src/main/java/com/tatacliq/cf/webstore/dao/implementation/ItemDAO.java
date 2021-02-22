package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.IItemDAO;
import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ResourceBundle;

public class ItemDAO implements IItemDAO {
    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public ItemDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    @Override
    public Item getItem(long itemId) throws SQLException {
        ResultSet resultSet;
        Item item;
        String query = resourceBundle.getString("getItem");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, itemId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        item = new Item();
        item.setItemId(resultSet.getLong(1));
        item.setName(resultSet.getString(2));
        item.setDescription(resultSet.getString(3));
        item.setPrice(resultSet.getDouble(4));
        item.setAvailable(resultSet.getBoolean(5));
        item.setProductId(resultSet.getLong(6));

        return item;
    }

    @Override
    public void updateItem(Item item) throws SQLException {
        String query = resourceBundle.getString("updateItem");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, item.getName());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setDouble(3, item.getPrice());
        preparedStatement.setBoolean(4, item.isAvailable());
        preparedStatement.setLong(5, item.getProductId());
        preparedStatement.setLong(6, item.getItemId());
        preparedStatement.executeUpdate();
        conn.commit();
    }
}
