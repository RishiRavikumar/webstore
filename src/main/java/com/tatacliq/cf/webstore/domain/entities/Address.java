package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.IAddress;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements IAddress {
    private long addressId;
    private String address;
    private String city;
    private String state;
    private String country;
    private long zip;
    private String type;
    private long phoneNumber;
}
