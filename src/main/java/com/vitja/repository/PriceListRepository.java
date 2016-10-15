package com.vitja.repository;

import com.vitja.model.PriceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Viktor on 11.10.2016.
 */
public interface PriceListRepository  extends CrudRepository<PriceList, Integer>{
    PriceList findPriceListByDescription(String description);
}
