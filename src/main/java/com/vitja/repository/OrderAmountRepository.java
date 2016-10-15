package com.vitja.repository;

import com.vitja.model.OrderAmount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Viktor on 15.10.2016.
 */
public interface OrderAmountRepository extends CrudRepository<OrderAmount, Integer> {
}
