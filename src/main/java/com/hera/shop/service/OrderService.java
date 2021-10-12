package com.hera.shop.service;

import com.hera.shop.dto.OrderDto;
import com.hera.shop.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order create(OrderDto orderDto);

    List<Order> list(Date from, Date to);
}
