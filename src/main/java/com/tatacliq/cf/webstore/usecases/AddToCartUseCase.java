package com.tatacliq.cf.webstore.usecases;


import com.tatacliq.cf.webstore.dao.implementation.CategoryDAO;
import com.tatacliq.cf.webstore.dao.implementation.ProductDAO;
import com.tatacliq.cf.webstore.domain.entities.*;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;
import com.tatacliq.cf.webstore.struts.actions.AddItemToCartAction;

import java.sql.SQLException;
import java.util.List;

public class AddToCartUseCase {
    public static void main(String[] args) throws SQLException {
        /*
            CategoryId 1: Footwear
            -> ProductId 101: U.S. Polo Assn. Loafers
                -> ItemId 1: Fresco Brown Sneakers
         */


        Address address = new Address();
        address.getCountry();


        IWebStoreFacade webStoreFacade = new WebStorePOJO();

        long categoryId = 1;

        Cart cart = webStoreFacade.createCart();
        Category category;
        List<Product> productList;
        List<Item> itemList;
        Product product;
        Item item;

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();

        category = categoryDAO.getCategory(categoryId);
        if(categoryDAO.hasProducts(category.getCategoryId())) {
            productList = categoryDAO.getProducts(categoryId);
            product = productList.get(0);

            itemList = productDAO.getItems(product.getProductId());
            item = itemList.get(0);

            AddItemToCartAction addItemToCartAction = new AddItemToCartAction(cart, item, product);
            addItemToCartAction.execute();
        } else {
            System.out.println("No Products found under this category");
        }
    }
}
