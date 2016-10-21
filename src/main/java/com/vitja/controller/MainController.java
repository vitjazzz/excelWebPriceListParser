package com.vitja.controller;

import com.vitja.exception.NoSuchOrderException;
import com.vitja.model.CustomerName;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
    public ModelAndView getIndexPage(Model model, ModelAndView modelAndView){
        if(priceListService.getAllPriceLists().isEmpty()){
            return new ModelAndView("priceListsPage");
        }

        modelAndView.setViewName("index");

        Order order = new Order();
        order.setPriceList(new PriceList());
        modelAndView.addObject("orderAmountModel", new OrderAmount(1, order));

        List<String> priceListDescriptions = new ArrayList<>();
        priceListService.getAllPriceLists().stream().forEach(priceList -> priceListDescriptions.add(priceList.getDescription()));
        modelAndView.addObject("allPriceListDescriptions", priceListDescriptions);

        modelAndView.addObject("allOrderAmounts", orderAmountService.getAllOrderAmounts());

        String errorMessage = (String) model.asMap().get("orderErrorMessage");
        modelAndView.addObject("orderErrorMessage", errorMessage);

        modelAndView.addObject("customerName", new CustomerName());

        return modelAndView;
    }

    @RequestMapping(value = "/countOrderPrice", method = RequestMethod.POST)
    public ModelAndView countOrderPrice(@ModelAttribute("orderAmountModel") OrderAmount orderAmount,
                                        RedirectAttributes redirectAttributes,
                                        ModelAndView modelAndView,
                                        @ModelAttribute("customerName") CustomerName customerName){
        PriceList priceList = priceListService.getPriceListByDescription(orderAmount.getOrder().getPriceList().getDescription());

        Order order = orderService.getOrderByCodeAndPriceList(orderAmount.getOrder().getCode(), priceList);
        if(order == null){
            redirectAttributes.addFlashAttribute("orderErrorMessage", "Товару з заданим кодом і прайс-листом не існує!");
            return new ModelAndView("redirect:/");
        }

        orderAmount.setOrder(order);
        orderAmountService.saveOrderAmount(orderAmount);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/priceListsPage")
    public ModelAndView getAddPriceListPage(ModelAndView modelAndView){
        modelAndView.setViewName("priceListsPage");
        modelAndView.addObject("priceListModel", new PriceList());
        modelAndView.addObject("allPriceLists", priceListService.getAllPriceLists());

        return modelAndView;
    }

    @RequestMapping(value = "/addPriceList", method = RequestMethod.POST)
    public ModelAndView parseExcelFile(@ModelAttribute("priceListModel") PriceList priceList,
                                 @RequestParam(value = "excelFile", name = "excelFile", required = false) MultipartFile multipartFile,
                                       ModelAndView modelAndView){
        if(multipartFile != null) {
            try {
                priceListService.parseExcelFile(convertToFile(multipartFile), priceList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        modelAndView.setViewName("redirect:/priceListsPage");
        return modelAndView;
    }

    @RequestMapping(value = "/removePriceList/{id}", method = RequestMethod.GET)
    public ModelAndView removePriceList(@PathVariable("id") Integer id, ModelAndView modelAndView){
        priceListService.remove(id);
        modelAndView.setViewName("redirect:/priceListsPage");

        return modelAndView;
    }

    @RequestMapping(value = "/removeOrderAmount/{id}", method = RequestMethod.GET)
    public ModelAndView removeOrderAmount(@PathVariable("id") Integer id, ModelAndView modelAndView){
        orderAmountService.removeOrderAmountById(id);
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(servletContext.getRealPath("/") + "/" + "temp");
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }
}
