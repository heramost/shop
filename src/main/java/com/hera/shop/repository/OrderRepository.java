package com.hera.shop.repository;

import com.hera.shop.model.Order;
import com.hera.shop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByTimestampBetween(Date from, Date to);
}
