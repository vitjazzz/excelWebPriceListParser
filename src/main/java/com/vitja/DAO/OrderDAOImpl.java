/*
package com.vitja.DAO;

import com.vitja.model.Order;
import com.vitja.model.PriceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

*/
/**
 * Created by Viktor on 10.10.2016.
 *//*

public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Set<Order> orders) {
        Session session = sessionFactory.getCurrentSession();
        orders.stream().forEach(order -> session.save(order));
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public Order getOrderByCodeAndPriceList(Integer code, PriceList priceList) {

        return null;
    }
}
*/
