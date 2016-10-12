package com.vitja.controller;

import com.vitja.service.OrderService;
import com.vitja.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Viktor on 12.10.2016.
 */
@Controller
public class MainController {
    @Autowired
    public OrderService orderService;

    @Autowired
    public PriceListService priceListService;

    @RequestMapping(value = "/")
    ModelAndView getIndexPage(){
        return new ModelAndView("index");
    }
}
