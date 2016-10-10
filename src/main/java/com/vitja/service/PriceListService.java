package com.vitja.service;

import com.vitja.DAO.OrderDAO;
import com.vitja.DAO.PriceListDAO;
import com.vitja.Logger;
import com.vitja.model.PriceList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Viktor on 10.10.2016.
 */
public class PriceListService {
    @Autowired
    private PriceListDAO priceListDAO;

    @Autowired
    private OrderDAO orderDAO;

    public void savePriceList(PriceList priceList){
        priceListDAO.save(priceList);
        Logger.LOGGER.info("Price List '" + priceList.getDescription() + "' saved...");
    }
}
