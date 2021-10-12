package com.hera.shop.dto;

import org.junit.jupiter.api.Test;

class PriceDtoTest extends ValidationTest {
    @Test
    public void priceTooLow() {
        validate(priceDto(0.000001), "Minimum price in USD not reached");
    }

    @Test
    public void properPrice() {
        validate(priceDto(21312));
    }

    private PriceDto priceDto(double priceInUsd) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPriceInUsd(priceInUsd);

        return priceDto;
    }
}