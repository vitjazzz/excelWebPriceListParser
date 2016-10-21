package com.vitja.service;

import com.vitja.Logger;
import com.vitja.model.OrderAmount;
import com.vitja.repository.OrderAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Viktor on 15.10.2016.
 */
@Service
@Transactional
public class OrderAmountService {
    @Autowired
    private OrderAmountRepository orderAmountRepository;

    @Transactional
    public void saveOrderAmount(OrderAmount orderAmount){
        Logger.LOGGER.info("Saving OrderAmount object...");
        orderAmountRepository.save(orderAmount);
    }

    @Transactional
    public List<OrderAmount> getAllOrderAmounts(){
        Logger.LOGGER.info("Get all objects from table OrderAmount.");
        return (List<OrderAmount>) orderAmountRepository.findAll();
    }

    @Transactional
    public void removeOrderAmountById(Integer id){
        Logger.LOGGER.info("Remove OrderAmount...");
        orderAmountRepository.delete(id);
    }
}
