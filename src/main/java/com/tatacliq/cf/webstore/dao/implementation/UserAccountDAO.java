package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.IUserAccountDAO;
import com.tatacliq.cf.webstore.domain.entities.Address;
import com.tatacliq.cf.webstore.domain.entities.CreditCard;
import com.tatacliq.cf.webstore.domain.entities.UserAccount;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ResourceBundle;

public class UserAccountDAO implements IUserAccountDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public UserAccountDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    @Override
    public void addUserAccount(UserAccount userAccount) throws SQLException {
        String query = resourceBundle.getString("addUser");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, userAccount.getUserAccountId());
        preparedStatement.setString(2, userAccount.getFirstName());
        preparedStatement.setString(3, userAccount.getLastName());
        preparedStatement.setDate(4, new Date(userAccount.getBirthDate().getTime()));
        preparedStatement.setString(5, userAccount.getEmailID());
        preparedStatement.setString(6, userAccount.getUserName());
        preparedStatement.setString(7, userAccount.getPassword());
        preparedStatement.setLong(8, userAccount.getAddress().getAddressId());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addAddress(Address address) throws SQLException {
        String query = resourceBundle.getString("addAddress");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, address.getAddressId());
        preparedStatement.setString(2, address.getAddress());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getState());
        preparedStatement.setString(5, address.getCountry());
        preparedStatement.setString(6, address.getType());
        preparedStatement.setLong(7, address.getZip());
        preparedStatement.setLong(8, address.getPhoneNumber());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public UserAccount getUserAccount(String userName, String password) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("getUser_username_password");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        UserAccount userAccount = new UserAccount();
        userAccount.setUserAccountId(resultSet.getLong(1));
        userAccount.setFirstName(resultSet.getString(2));
        userAccount.setLastName(resultSet.getString(3));
        userAccount.setBirthDate(resultSet.getDate(4));
        userAccount.setEmailID(resultSet.getString(5));
        userAccount.setUserName(resultSet.getString(6));
        userAccount.setPassword(resultSet.getString(7));
        userAccount.setAddressId(resultSet.getLong(8));
        return userAccount;
    }

    @Override
    public UserAccount getUserAccount(String userName) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("getUser_username");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, userName);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        UserAccount userAccount = new UserAccount();
        userAccount.setUserAccountId(resultSet.getLong(1));
        userAccount.setFirstName(resultSet.getString(2));
        userAccount.setLastName(resultSet.getString(3));
        userAccount.setBirthDate(resultSet.getDate(4));
        userAccount.setEmailID(resultSet.getString(5));
        userAccount.setUserName(resultSet.getString(6));
        userAccount.setPassword(resultSet.getString(7));
        userAccount.setAddressId(resultSet.getLong(8));
        return userAccount;
    }
    @Override
    public UserAccount getUserAccount(long userId) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("getUser_id");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();
        UserAccount userAccount = new UserAccount();
        userAccount.setUserAccountId(resultSet.getLong(1));
        userAccount.setFirstName(resultSet.getString(2));
        userAccount.setLastName(resultSet.getString(3));
        userAccount.setBirthDate(resultSet.getDate(4));
        userAccount.setEmailID(resultSet.getString(5));
        userAccount.setUserName(resultSet.getString(6));
        userAccount.setPassword(resultSet.getString(7));
        userAccount.setAddressId(resultSet.getLong(8));
        return userAccount;
    }

    @Override
    public void removeUserAccount(long userId) throws SQLException {
        String query = resourceBundle.getString("removeUser");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) {

    }

    @Override
    public CreditCard getCreditCard(long creditCardNumber) {
        return null;
    }
}
