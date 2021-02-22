package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.ICreditCard;
import lombok.Data;

import java.util.Date;

@Data
public class CreditCard implements ICreditCard {
    private long cardId;
    private String creditCardNumber;
    private String cardType;
    private Date expiryDate;

}
