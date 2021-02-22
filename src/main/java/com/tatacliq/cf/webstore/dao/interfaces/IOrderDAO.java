package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order) throws SQLException;
    void addOrder(long orderId, long itemId, long oId) throws SQLException;
    void addOrder(long orderId, long productId, long quantity, long oPId) throws SQLException;
    Order getOrder(long orderId) throws SQLException;
    List<Order> getOrders(String userName) throws SQLException;

}
