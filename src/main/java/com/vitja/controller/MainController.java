package com.vitja.controller;

import com.vitja.model.PriceList;
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
import java.io.File;
import java.io.IOException;

/**
 * Created by Viktor on 12.10.2016.
 */
@Controller
public class MainController implements ServletContextAware {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PriceListService priceListService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/")
    public ModelAndView getIndexPage(){
        return new ModelAndView("index");
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

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(servletContext.getRealPath("/") + "/" + "temp");
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }
}
