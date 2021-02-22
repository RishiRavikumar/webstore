package com.hcl.cf.webstore.test.junit;

import com.tatacliq.cf.webstore.dao.implementation.OrderDAO;
import com.tatacliq.cf.webstore.domain.entities.Order;
import com.tatacliq.cf.webstore.domain.interfaces.IWebStoreFacade;
import com.tatacliq.cf.webstore.facade.WebStorePOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("FieldCanBeLocal")
public class TestWebStorePOJO_Select {
    private IWebStoreFacade webStoreFacade;

    @Mock
    private Order mockOrder;


    @BeforeEach
    void setUp() {
        webStoreFacade = new WebStorePOJO();
        mockOrder = webStoreFacade.createOrder();
        mockOrder.setOrderId(144);
    }

    @Test
    @DisplayName("Test get order (Mockito)")
    public void getOrder() throws SQLException {
        OrderDAO orderDAO = mock(OrderDAO.class);

        when(orderDAO.getOrder(144)).thenReturn(mockOrder);
        assertEquals(orderDAO.getOrder(144).getOrderId(), 144L);
    }
}
