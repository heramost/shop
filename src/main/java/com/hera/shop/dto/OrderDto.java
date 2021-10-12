package com.hera.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    @Valid
    @NotEmpty(message = "Products must be added to the order")
    private List<OrderItemDto> items;

    @NotEmpty(message = "Buyer's email address must be set")
    private String buyerEmail;
}
