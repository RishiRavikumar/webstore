package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.UserAccountDAO;
import com.tatacliq.cf.webstore.domain.entities.Address;
import com.tatacliq.cf.webstore.domain.entities.UserAccount;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class RegisterUserAction {
    private Address address;
    UserAccount userAccount;

    public void execute() throws SQLException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();

        userAccountDAO.addAddress(address);
        userAccountDAO.addUserAccount(userAccount);
    }
}
