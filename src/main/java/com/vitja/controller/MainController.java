package com.vitja.controller;

import com.vitja.exception.NoSuchOrderException;
import com.vitja.model.Order;
import com.vitja.model.OrderAmount;
import com.vitja.model.PriceList;
import com.vitja.repository.OrderAmountRepository;
import com.vitja.service.OrderAmountService;
import com.vitja.service.OrderService;
import com.vitja.service.PriceListService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor on 12.10.2016.
 */
@Controller
public class MainController implements ServletContextAware {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private OrderAmountService orderAmountService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/")
    public ModelAndView getIndexPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Order order = new Order();
        order.setPriceList(new PriceList());
        modelAndView.addObject("orderAmountModel", new OrderAmount(1, order));

        List<String> priceListDescriptions = new ArrayList<>();
        priceListService.getAllPriceLists().stream().forEach(priceList -> priceListDescriptions.add(priceList.getDescription()));
        modelAndView.addObject("allPriceListDescriptions", priceListDescriptions);

        modelAndView.addObject("allOrderAmounts", orderAmountService.getAllOrderAmounts());

        return modelAndView;
    }

    @RequestMapping(value = "/countOrderPrice", method = RequestMethod.POST)
    public ModelAndView countOrderPrice(@ModelAttribute("orderAmountModel") OrderAmount orderAmount){
        PriceList priceList = priceListService.getPriceListByDescription(orderAmount.getOrder().getPriceList().getDescription());

        Order order = orderService.getOrderByCodeAndPriceList(orderAmount.getOrder().getCode(), priceList);
        if(order == null){
            throw new NoSuchOrderException("Товару з заданим кодом і прайс-листом не існує!");
        }

        orderAmount.setOrder(order);
        orderAmountService.saveOrderAmount(orderAmount);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/priceListsPage")
    public ModelAndView getAddPriceListPage(){
        ModelAndView modelAndView = new ModelAndView("priceListsPage");
        modelAndView.addObject("priceListModel", new PriceList());
        modelAndView.addObject("allPriceLists", priceListService.getAllPriceLists());

        return modelAndView;
    }

    @RequestMapping(value = "/addPriceList", method = RequestMethod.POST)
    public String parseExcelFile(@ModelAttribute("priceListModel") PriceList priceList,
                                 @RequestParam(value = "excelFile", name = "excelFile", required = false) MultipartFile multipartFile){
        if(multipartFile != null) {
            try {
                priceListService.parseExcelFile(convertToFile(multipartFile), priceList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/priceListsPage";
    }

    @RequestMapping(value = "/removePriceList/{id}", method = RequestMethod.GET)
    public String removePriceList(@PathVariable("id") Integer id){
        priceListService.remove(id);
        return "redirect:/priceListsPage";
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ModelAndView handleNoSuchOrderException(HttpServletRequest request, Exception ex){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("orderErrorMessage", ex.getMessage());

        return modelAndView;
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(servletContext.getRealPath("/") + "/" + "temp");
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }
}
