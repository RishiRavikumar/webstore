package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Order implements IOrder {
    private long orderId;
//    private long userAccountId;
    private UserAccount UserAccount;
//    private long creditCardId;
    private CreditCard CreditCard;
    private Cart Cart;
    private Address shippingAddress;
    private Date date;
    private String orderStatus;
}
