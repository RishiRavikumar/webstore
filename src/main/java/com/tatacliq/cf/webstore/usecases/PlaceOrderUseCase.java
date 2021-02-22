package com.tatacliq.cf.webstore.usecases;

import com.tatacliq.cf.webstore.dao.implementation.CategoryDAO;
import com.tatacliq.cf.webstore.dao.implementation.ProductDAO;
import com.tatacliq.cf.webstore.domain.entities.Cart;
import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.entities.Item;
import com.tatacliq.cf.webstore.domain.entities.Product;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;
import com.tatacliq.cf.webstore.struts.actions.AddItemToCartAction;
import com.tatacliq.cf.webstore.struts.actions.ClearCartAndOrderAction;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PlaceOrderUseCase {
    public static void main(String[] args) throws SQLException {
        IWebStoreFacade webStoreFacade = new WebStorePOJO();


        /*
            Adding Item to cart
         */
        long categoryId = 1;

        Cart cart = webStoreFacade.createCart();
        Category category;
        List<Product> productList;
        List<Item> itemList;
        Product product;

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();

        category = categoryDAO.getCategory(categoryId);
        if(categoryDAO.hasProducts(category.getCategoryId())) {
            productList = categoryDAO.getProducts(categoryId);
            product = productList.get(0);

            itemList = productDAO.getItems(product.getProductId());

            AddItemToCartAction addItemToCartAction = new AddItemToCartAction();
            addItemToCartAction.setCart(cart);
            addItemToCartAction.setProduct(product);

            for (Item item: itemList) {
                addItemToCartAction.setItem(item);
                addItemToCartAction.execute();
            }
        } else {
            System.out.println("No Products found under this category");
        }

        /*
            Placing Order
         */
        Date date = new Date();
        long userId = 8408;
        long creditCardId = 1;
        long addressId = 949;

        ClearCartAndOrderAction clearCartAndOrderAction = new ClearCartAndOrderAction(cart, date, userId, creditCardId, addressId);
        clearCartAndOrderAction.execute();
    }
}
