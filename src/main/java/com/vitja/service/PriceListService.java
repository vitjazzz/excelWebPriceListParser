package com.vitja.service;

import com.vitja.Logger;
import com.vitja.model.Order;
import com.vitja.model.PriceList;
import com.vitja.repository.OrderRepository;
import com.vitja.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

/**
 * Created by Viktor on 10.10.2016.
 */
@Service
@Transactional
public class PriceListService {
    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExcelParser excelParser;

    @Transactional
    public void savePriceList(PriceList priceList){
        priceListRepository.save(priceList);
        Logger.LOGGER.info("Price List '" + priceList.getDescription() + "' saved...");
    }

    @Transactional
    public void savePriceListAndOrders(PriceList priceList, Set<Order> orders){
        priceListRepository.save(priceList);
        orderRepository.save(orders);
        Logger.LOGGER.info("Price List '" + priceList.getDescription() + "' and orders saved...");
    }

    @Transactional
    public PriceList getPriceListByDescription(String description){
        return priceListRepository.findPriceListByDescription(description);
    }

    @Transactional
    public List<PriceList> getAllPriceLists(){
        return (List<PriceList>) priceListRepository.findAll();
    }

    @Transactional
    public void remove(Integer id){
        priceListRepository.delete(id);
    }

    public void parseExcelFile(File file, PriceList priceList){
        //PriceList priceList = new PriceList(priceListDescription);
        Set<Order> orders = excelParser.parseFile(file);
        orders.stream().forEach(order -> order.setPriceList(priceList));
        savePriceListAndOrders(priceList, orders);

        deleteFile(file);
    }

    private void deleteFile(File file){
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
