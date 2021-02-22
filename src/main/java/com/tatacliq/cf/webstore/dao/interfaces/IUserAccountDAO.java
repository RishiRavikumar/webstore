package com.tatacliq.cf.webstore.dao.interfaces;

import com.tatacliq.cf.webstore.domain.entities.Address;
import com.tatacliq.cf.webstore.domain.entities.CreditCard;
import com.tatacliq.cf.webstore.domain.entities.UserAccount;

import java.sql.SQLException;

public interface IUserAccountDAO {
    void addUserAccount(UserAccount userAccount) throws SQLException;
    void addAddress(Address address) throws SQLException;
    UserAccount getUserAccount(String userName, String password) throws SQLException;
    UserAccount getUserAccount(String userName) throws SQLException;
    UserAccount getUserAccount(long userId) throws SQLException;
    void removeUserAccount(long userId) throws SQLException;
    void updateUserAccount(UserAccount userAccount);
    CreditCard getCreditCard(long creditCardNumber);
}
