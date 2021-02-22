package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.IOrderDAO;
import com.tatacliq.cf.webstore.dao.interfaces.IUserAccountDAO;
import com.tatacliq.cf.webstore.domain.entities.*;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDAO implements IOrderDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public OrderDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    @Override
    public void addOrder(Order order) throws SQLException {
        String query = resourceBundle.getString("addOrder");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, order.getOrderId());
        preparedStatement.setString(2, order.getOrderStatus());
        preparedStatement.setDate(3, new Date(order.getDate().getTime()));
        preparedStatement.setLong(4, order.getUserAccount().getUserAccountId());
        preparedStatement.setLong(5, order.getCreditCard().getCardId());
        preparedStatement.setLong(6, order.getShippingAddress().getAddressId());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addOrder(long orderId, long itemId, long oId) throws SQLException {
        String query = resourceBundle.getString("addOrderItem");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, orderId);
        preparedStatement.setLong(2, itemId);
        preparedStatement.setLong(3, oId);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addOrder(long orderId, long productId, long quantity, long oPId) throws SQLException {
        String query = resourceBundle.getString("addOrderProduct");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, orderId);
        preparedStatement.setLong(2, quantity);
        preparedStatement.setLong(3, productId);
        preparedStatement.setLong(4, oPId);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    public Address getAddress(long addressId) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("getAddress");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, addressId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Address address = new Address();
        address.setAddressId(resultSet.getLong(1));
        address.setAddress(resultSet.getString(2));
        address.setCity(resultSet.getString(3));
        address.setState(resultSet.getString(4));
        address.setCountry(resultSet.getString(5));
        address.setType(resultSet.getString(6));
        address.setZip(resultSet.getLong(7));
        address.setPhoneNumber(resultSet.getLong(8));
        return address;
    }

    public CreditCard getCreditCard(long creditCardId) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("getCreditCard_id");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, creditCardId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        CreditCard creditCard = new CreditCard();
        creditCard.setCardId(resultSet.getLong(1));
        creditCard.setCreditCardNumber(resultSet.getString(2));
        creditCard.setCardType(resultSet.getString(3));
        creditCard.setExpiryDate(resultSet.getDate(4));
        return creditCard;
    }

    @Override
    public Order getOrder(long orderId) throws SQLException {
        ResultSet resultSet;
        Order order = null;
        
        IUserAccountDAO userAccountDAO = new UserAccountDAO();

        String query = resourceBundle.getString("getOrder");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, orderId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            order = new Order();
            order.setOrderId(resultSet.getLong(1));
            order.setOrderStatus(resultSet.getString(2));
            order.setDate(resultSet.getDate(3));
            order.setUserAccount(userAccountDAO.getUserAccount(resultSet.getLong(4)));
            order.setCreditCard(getCreditCard(resultSet.getLong(5)));
            order.setShippingAddress(getAddress(resultSet.getLong(6)));
        }
        return order;
    }

    @Override
    public List<Order> getOrders(String userName) throws SQLException {
        ResultSet resultSet;
        IUserAccountDAO userAccountDAO = new UserAccountDAO();
        List<Order> orders = new ArrayList<>();
        Order order;

        long userId = userAccountDAO.getUserAccount(userName).getUserAccountId();
        String query = resourceBundle.getString("getOrders");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            order = new Order();
            order.setOrderId(resultSet.getLong(1));
            order.setOrderStatus(resultSet.getString(2));
            order.setDate(resultSet.getDate(3));
            order.setUserAccount(userAccountDAO.getUserAccount(resultSet.getLong(4)));
            order.setCreditCard(getCreditCard(resultSet.getLong(5)));
            order.setShippingAddress(getAddress(resultSet.getLong(6)));
            orders.add(order);
        }
        return orders;
    }
}
