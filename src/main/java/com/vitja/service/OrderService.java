package com.vitja.service;

import com.vitja.Logger;
import com.vitja.model.Order;
import com.vitja.model.OrderAmount;
import com.vitja.model.PriceList;
import com.vitja.repository.OrderAmountRepository;
import com.vitja.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderAmountRepository orderAmountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void saveOrder(Order order){
        orderRepository.save(order);
        Logger.LOGGER.info("Order " + order.getCode() + " - " + order.getName() + " saved...");
    }

    @Transactional
    public void saveOrders(Set<Order> orders){
        orderRepository.save(orders);
        Logger.LOGGER.info("Set of orders saved...");
    }

    @Transactional
    public Order getOrderByCodeAndPriceList(Integer code, PriceList priceList){
        Logger.LOGGER.info("Get order from database...");
        return orderRepository.findOrderByCodeAndPriceList(code, priceList);
    }
}
