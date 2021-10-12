package com.hera.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PriceDto {

    @DecimalMin(value = "0.01", message = "Minimum price in USD not reached")
    private double priceInUsd;
}
