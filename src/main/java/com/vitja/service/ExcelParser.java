package com.vitja.service;

import com.vitja.model.Order;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Viktor on 09.10.2016.
 */
@SuppressWarnings("deprecation")
@Service
public class ExcelParser {
    public Set<Order> parseFile(File file) {
        Set<Order> orders = new HashSet<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row == null){
                    break;
                }
                Order order = new Order();

                Cell cell = row.getCell(1);
                if(cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC){
                    continue;
                }
                order.setCode(convertDoubleToInt(cell.getNumericCellValue()));

                order.setLabel(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());

                order.setPage(row.getCell(3) == null ? 0 : convertDoubleToInt(row.getCell(3).getNumericCellValue()));

                order.setName(row.getCell(4).getStringCellValue());

                order.setBonusPoint(row.getCell(5) == null ? 0 : convertDoubleToInt(row.getCell(5).getNumericCellValue()));

                order.setSalesVolume(row.getCell(6) == null ? 0.0 : row.getCell(6).getNumericCellValue());

                order.setDistributionPrice(row.getCell(7) == null ? 0.0 : row.getCell(7).getNumericCellValue());

                order.setPurchasingPrice(row.getCell(8) == null ? 0.0 : row.getCell(8).getNumericCellValue());

                orders.add(order);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Integer convertDoubleToInt(double d){
        return Double.valueOf(d).intValue();
    }
}
