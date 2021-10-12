package com.hera.shop.service.impl;

import com.hera.shop.dto.OrderDto;
import com.hera.shop.dto.OrderItemDto;
import com.hera.shop.model.Order;
import com.hera.shop.model.Product;
import com.hera.shop.repository.OrderRepository;
import com.hera.shop.service.ProductService;
import com.hera.shop.utl.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    ProductService productService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    DateUtil dateUtil;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void buyerEmailMustBeValid() {
        //arrange
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerEmail("some invalid email");

        //act
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> orderService.create(orderDto));

        //assert
        assertThat(responseStatusException.getStatus()).isEqualTo(BAD_REQUEST);
        assertThat(responseStatusException.getReason()).isEqualTo("Buyer's email address is invalid");
    }

    @Test
    public void orderCreated() {
        //arrange
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerEmail("asd@gmail.com");

        orderDto.setItems(asList(orderItemDto(1L, 10),
                orderItemDto(1L, 5),
                orderItemDto(2L, 100)));

        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        Product product1 = product(0.5);
        Product product2 = product(1.212);
        when(productService.get(1L)).thenReturn(product1);
        when(productService.get(2L)).thenReturn(product2);

        Date now = new Date();
        when(dateUtil.now()).thenReturn(now);

        //act
        Order order = orderService.create(orderDto);

        //assert
        assertThat(order.getBuyerEmail()).isEqualTo("asd@gmail.com");
        assertThat(order.getTimestamp()).isEqualTo(now);
        assertThat(order.getCalculatedPriceInUsd()).isEqualTo(128.7);
        assertThat(order.getItems()).hasSize(2);
        assertThat(order.getItems()).anyMatch(orderItem -> orderItem.getProduct().equals(product1) && orderItem.getQuantity() == 15);
        assertThat(order.getItems()).anyMatch(orderItem -> orderItem.getProduct().equals(product2) && orderItem.getQuantity() == 100);
    }

    @Test
    public void ordersFoundBetweenDates() {
        //arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = asList(order1, order2);

        Date date1 = new Date();
        Date date2 = new Date();
        when(orderRepository.findAllByTimestampBetween(eq(date1), eq(date2))).thenReturn(orders);

        //act
        List<Order> ordersReturned = orderService.list(date1, date2);

        //assert
        assertThat(ordersReturned).isEqualTo(orders);
    }

    private Product product(double priceInUsd) {
        Product product = new Product();
        product.setPriceInUsd(priceInUsd);
        return product;
    }

    private OrderItemDto orderItemDto(long productId, int quantity) {
        OrderItemDto orderItemDto1 = new OrderItemDto();
        orderItemDto1.setProductId(productId);
        orderItemDto1.setQuantity(quantity);
        return orderItemDto1;
    }
}