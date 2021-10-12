package com.hera.shop.service.impl;

import com.hera.shop.dto.OrderDto;
import com.hera.shop.dto.OrderItemDto;
import com.hera.shop.model.Order;
import com.hera.shop.model.OrderItem;
import com.hera.shop.repository.OrderRepository;
import com.hera.shop.service.OrderService;
import com.hera.shop.service.ProductService;
import com.hera.shop.utl.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final DateUtil dateUtil;

    @Override
    public Order create(OrderDto orderDto) {
        Order order = new Order();
        setEmailAddress(orderDto, order);
        order.setCalculatedPriceInUsd(0);

        orderDto.getItems().stream()
                .collect(groupingBy(OrderItemDto::getProductId, summingInt(OrderItemDto::getQuantity)))
                .forEach((productId, quantitySum) -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(productService.get(productId));
                    orderItem.setQuantity(quantitySum);
                    order.addItem(orderItem);
                    order.setCalculatedPriceInUsd(order.getCalculatedPriceInUsd() + orderItem.getQuantity() * orderItem.getProduct().getPriceInUsd());
                });

        order.setTimestamp(dateUtil.now());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> list(Date from, Date to) {
        return orderRepository.findAllByTimestampBetween(from, to);
    }

    private void setEmailAddress(OrderDto orderDto, Order order) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(orderDto.getBuyerEmail())) {
            order.setBuyerEmail(orderDto.getBuyerEmail());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buyer's email address is invalid");
        }
    }

}
