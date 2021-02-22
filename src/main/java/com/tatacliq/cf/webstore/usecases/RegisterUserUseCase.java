package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.domain.entities.Address;
import com.tatacliq.cf.webstore.domain.entities.UserAccount;
import com.tatacliq.cf.webstore.struts.actions.RegisterUserAction;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class RegisterUserUseCase {
    public static void main(String[] args) throws SQLException {
        Address address = getAddress();
        UserAccount userAccount= getUserAcc(address);
        new RegisterUserAction(address, userAccount).execute();
    }

    public static UserAccount getUserAcc(Address address) {
        Scanner scanner = new Scanner(System.in);

        long userAccountId = new Random().nextInt(10000);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        if(username.equals("")) {
            username = RandomStringUtils.randomAlphabetic(5)+ new Random().nextInt(10000);
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();
        if(password.equals("")) {
            password = RandomStringUtils.randomAlphabetic(5)+ new Random().nextInt(10000);
        }
        System.out.println("Enter firstName:");
        String firstName = scanner.nextLine();
        if(firstName.equals("")) {
            firstName = RandomStringUtils.randomAlphabetic(5)+ new Random().nextInt(10000);
        }
        System.out.println("Enter lastName:");
        String lastName = scanner.nextLine();
        if(lastName.equals("")) {
            lastName = RandomStringUtils.randomAlphabetic(5)+ new Random().nextInt(10000);
        }
        System.out.println("Enter emailId:");
        String emailId = scanner.nextLine();
        if(emailId.equals("")) {
            emailId = RandomStringUtils.randomAlphabetic(5)+ new Random().nextInt(10000) +"@xyz.com";
        }

        Date birthDate = new Date(Math.abs(System.currentTimeMillis()));

        return new UserAccount(userAccountId, address.getAddressId(), address, username, password, firstName, lastName, birthDate, emailId);
    }

    public static Address getAddress() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Address:");
        String addressStr = scanner.nextLine();
        if(addressStr.equals("")) {
            addressStr = new Random().nextInt(10000) + "St, " + RandomStringUtils.randomAlphabetic(20);
        }

        System.out.println("Enter city:");
        String city = scanner.nextLine();
        if(city.equals("")) {
            city = RandomStringUtils.randomAlphabetic(5);
        }

        System.out.println("Enter state:");
        String state = scanner.nextLine();
        if(state.equals("")) {
            state = RandomStringUtils.randomAlphabetic(7);
        }

        System.out.println("Enter country:");
        String country = scanner.nextLine();
        if(country.equals("")) {
            country = RandomStringUtils.randomAlphabetic(9);
        }

//        System.out.println("Enter zip:");
        long zip = new Random().nextInt(10000000);

        System.out.println("Enter type:");
        String type = scanner.nextLine();
        if(type.equals("")) {
            type = "Home";
        }

//        System.out.println("Enter phone number:");
        long phoneNumber = new Random().nextInt(10000000);

        long addressId = new Random().nextInt(1000);
        return new Address(addressId, addressStr, city, state, country, zip, type, phoneNumber);
    }
}
