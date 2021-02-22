package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IUserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements IUserAccount {
    private long userAccountId;
    private long addressId;
    private Address Address;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String emailID;
}
