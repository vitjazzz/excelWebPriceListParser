package com.vitja.DAO;

import com.vitja.model.Order;
import com.vitja.model.PriceList;

import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
public interface OrderDAO {
    void save(Order order);

    void save(Set<Order> orders);

    Order getOrderByCodeAndPriceList(Integer code, PriceList priceList);
}
