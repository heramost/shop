package com.hera.shop.dto;

import org.junit.jupiter.api.Test;

class OrderItemDtoTest extends ValidationTest {
    @Test
    public void quantityMinimumNotReached() {
        validate(orderItemDto(0, 1L), "Minimum order quantity not reached for product");
    }

    @Test
    public void productIdNotSet() {
        validate(orderItemDto(12, null), "Product id must be specified");
    }

    @Test
    public void properOrderItemDto() {
        validate(orderItemDto(100, 1L));
    }

    private OrderItemDto orderItemDto(int quantity, Long productId) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(quantity);
        orderItemDto.setProductId(productId);

        return orderItemDto;
    }
}