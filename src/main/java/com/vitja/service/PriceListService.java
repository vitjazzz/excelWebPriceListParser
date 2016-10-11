package com.vitja.service;

import com.vitja.DAO.OrderDAO;
import com.vitja.DAO.PriceListDAO;
import com.vitja.Logger;
import com.vitja.model.Order;
import com.vitja.model.PriceList;
import com.vitja.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
@Service
@Transactional
public class PriceListService {
    @Autowired
    private PriceListDAO priceListDAO;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void savePriceList(PriceList priceList){
        priceListDAO.save(priceList);
        Logger.LOGGER.info("Price List '" + priceList.getDescription() + "' saved...");
    }

    @Transactional
    public void savePriceListAndOrders(PriceList priceList, Set<Order> orders){
        priceListDAO.save(priceList);
        orderDAO.save(orders);
        Logger.LOGGER.info("Price List '" + priceList.getDescription() + "' and orders saved...");
    }

    @Transactional
    public PriceList getPriceListByDescription(String description){
        return priceListRepository.findPriceListByDescription(description);
    }
}
