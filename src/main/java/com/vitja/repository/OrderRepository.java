package com.vitja.repository;

import com.vitja.model.Order;
import com.vitja.model.PriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Viktor on 11.10.2016.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findOrderByCodeAndPriceList(Integer code, PriceList priceList);
}