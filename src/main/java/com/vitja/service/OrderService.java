package com.vitja.service;

import com.vitja.DAO.OrderDAO;
import com.vitja.Logger;
import com.vitja.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public void saveOrder(Order order){
        orderDAO.save(order);
        Logger.LOGGER.info("Order " + order.getCode() + " - " + order.getName() + " saved...");
    }

    public void saveOrders(Set<Order> orders){
        orderDAO.save(orders);
        Logger.LOGGER.info("Set of orders saved...");
    }
}
