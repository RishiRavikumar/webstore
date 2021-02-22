package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Item;

import java.sql.SQLException;

public interface IItemDAO {
    Item getItem(long itemId) throws SQLException;
    void updateItem(Item item) throws SQLException;
}
