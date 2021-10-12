package com.hera.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemDto {

    @NotNull(message = "Product id must be specified")
    private Long productId;

    @Min(value = 1, message = "Minimum order quantity not reached for product")
    private int quantity;
}
