package com.hera.shop.dto;

import org.junit.jupiter.api.Test;

class ProductDtoTest extends ValidationTest {
    @Test
    public void nameEmpty() {
        validate(product(""), "Product name must be set");
    }

    @Test
    public void nameNull() {
        validate(product(null), "Product name must be set");
    }

    @Test
    public void properProductDto() {
        validate(product("Nice name"));
    }

    private ProductDto product(String name) {
        ProductDto productDto = new ProductDto();
        productDto.setPriceInUsd(21312);
        productDto.setName(name);

        return productDto;
    }
}