package com.vitja;

import com.vitja.model.Order;
import com.vitja.model.PriceList;
import com.vitja.service.OrderService;
import com.vitja.service.PriceListService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by Viktor on 09.10.2016.
 */
public class MainExe {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        /*File file = new File("src/main/resources/temp.xlsx");

        Set<Order> orders = new ExcelParser().parseFile(file);

        PriceList priceList = new PriceList("First price list");

        orders.stream().forEach(order -> order.setPriceList(priceList));*/

        PriceListService priceListService = (PriceListService) context.getBean("priceListService");

        PriceList priceList = priceListService.getPriceListByDescription("First price list");

        Logger.LOGGER.info(priceList.getId() + "  " + priceList.getDescription());

        OrderService orderService = (OrderService) context.getBean("orderService");

        Order order = orderService.getOrderByCodeAndPriceList(11367, priceList);

        Logger.LOGGER.info(order.getCode() + "  " + order.getName());

        //priceListService.savePriceListAndOrders(priceList, orders);
        //orderService.saveOrders(orders);

    }
}
