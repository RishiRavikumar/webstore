package com.tatacliq.cf.webstore.facade;

import com.tatacliq.cf.webstore.domain.entities.*;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;

public class WebStorePOJO implements IWebStoreFacade {
    @Override
    public Address createAddress() {
        return new Address();
    }

    @Override
    public Catalog createCatalog() {
        return new Catalog();
    }

    @Override
    public Category createCategory() {
        return new Category();
    }

    @Override
    public Cart createCart() {
        return new Cart();
    }

    @Override
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    @Override
    public Item createItem() {
        return new Item();
    }

    @Override
    public Image createImage() {
        return new Image();
    }

    @Override
    public Product createProduct() {
        return new Product();
    }

    @Override
    public Order createOrder() {
        return new Order();
    }

    @Override
    public UserAccount createUserAccount() {
        return new UserAccount();
    }
}
