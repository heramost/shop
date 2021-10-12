package com.hera.shop.rest;

import com.hera.shop.dto.OrderDto;
import com.hera.shop.model.Order;
import com.hera.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    private final OrderService orderService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Order create(@Valid @RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @GetMapping
    public List<Order> list(@RequestParam("from") @DateTimeFormat(pattern = DATE_PATTERN) Date from,
                            @RequestParam("to") @DateTimeFormat(pattern = DATE_PATTERN) Date to) {
        return orderService.list(from, to);
    }

}
