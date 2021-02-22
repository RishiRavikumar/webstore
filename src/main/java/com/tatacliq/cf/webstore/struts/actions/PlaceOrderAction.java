package com.tatacliq.cf.webstore.struts.actions;

import com.tatacliq.cf.webstore.dao.implementation.OrderDAO;
import com.tatacliq.cf.webstore.dao.implementation.UserAccountDAO;
import com.tatacliq.cf.webstore.domain.entities.Cart;
import com.tatacliq.cf.webstore.domain.entities.Order;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
public class PlaceOrderAction {
    private Cart cart;
    private Date date;
    private long userId;
    private long creditCardId;
    private long addressId;

    public void execute() throws SQLException {
        IWebStoreFacade webStoreFacade = new WebStorePOJO();

        OrderDAO orderDAO = new OrderDAO();
        UserAccountDAO userAccountDAO = new UserAccountDAO();

        Order order = webStoreFacade.createOrder();

        long orderId = new Random().nextInt(10000);
        order.setOrderId(orderId);
        order.setCart(cart);
        order.setOrderStatus("Accepted");
        order.setDate(date);
        order.setUserAccount(userAccountDAO.getUserAccount(userId));
        order.setCreditCard(orderDAO.getCreditCard(creditCardId));
        order.setShippingAddress(orderDAO.getAddress(addressId));

        orderDAO.addOrder(order);

        cart.getItems()
                .stream()
                .forEach(item -> {
                    long oId = new Random().nextInt(10000);
                    try {
                        orderDAO.addOrder(orderId, item.getItemId(), oId);
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                });

        cart.getProducts()
                .stream()
                .forEach(cartProduct -> {
                    long oPId = new Random().nextInt(10000);
                    try {
                        orderDAO.addOrder(orderId, cartProduct.getProduct().getProductId(), cartProduct.getQuantity(), oPId);
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                });

        System.out.println("Cart Total: " + cart.getItemTotal());
        System.out.println("Order Details: " + order);
    }

}
