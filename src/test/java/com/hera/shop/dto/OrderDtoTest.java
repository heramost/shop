package com.hera.shop.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class OrderDtoTest extends ValidationTest {
    @Test
    public void buyerEmailNotSet() {
        validate(orderDto("", validOrderItemDto()), "Buyer's email address must be set");
    }

    @Test
    public void invalidItem() {
        validate(orderDto("asd@gmail.com", validOrderItemDto(), invalidOrderItemDto()), "Product id must be specified");
    }

    @Test
    public void properOrderDto() {
        validate(orderDto("asd@gmail.com", validOrderItemDto(), validOrderItemDto(), validOrderItemDto()));
    }

    private OrderDto orderDto(String buyerEmail, OrderItemDto... orderItemDtos) {
        OrderDto orderDto = new OrderDto();
        orderDto.setItems(Arrays.asList(orderItemDtos));
        orderDto.setBuyerEmail(buyerEmail);

        return orderDto;
    }

    private OrderItemDto validOrderItemDto() {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(1);
        orderItemDto.setProductId(1L);

        return orderItemDto;
    }

    private OrderItemDto invalidOrderItemDto() {
        OrderItemDto orderItemDto = validOrderItemDto();
        orderItemDto.setProductId(null);

        return orderItemDto;
    }
}