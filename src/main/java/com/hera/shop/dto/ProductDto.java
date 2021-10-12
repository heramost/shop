package com.hera.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProductDto extends PriceDto{

    @NotEmpty(message = "Product name must be set")
    private String name;
}
