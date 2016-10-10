package com.vitja.DAO;

import com.vitja.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Set<Order> orders) {
        Session session;

        session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        orders.stream().forEach(order -> session.save(order));

        transaction.commit();

        session.close();
    }

    public void save(Order order) {

    }
}
