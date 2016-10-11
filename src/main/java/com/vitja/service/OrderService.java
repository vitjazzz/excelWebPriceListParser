package com.vitja.service;

import com.vitja.DAO.OrderDAO;
import com.vitja.Logger;
import com.vitja.model.Order;
import com.vitja.model.PriceList;
import com.vitja.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
@Transactional
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void saveOrder(Order order){
        orderDAO.save(order);
        Logger.LOGGER.info("Order " + order.getCode() + " - " + order.getName() + " saved...");
    }

    @Transactional
    public void saveOrders(Set<Order> orders){
        orderDAO.save(orders);
        Logger.LOGGER.info("Set of orders saved...");
    }

    @Transactional
    public Order getOrderByCodeAndPriceList(Integer code, PriceList priceList){
        Logger.LOGGER.info("Get order from database...");
        return orderRepository.findOrderByCodeAndPriceList(code, priceList);
    }
}
